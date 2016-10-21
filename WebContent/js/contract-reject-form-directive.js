angular.module('htApp')
	.directive('contractRejectForm', function() {

		return {
			restrict: 'E',
			scope: {
				showRejectForm: '=',
				contract: '=',
				conciergeCarer: '='
			},
			controller: 'ContractRejectFormCtrl',
			controllerAs: 'contractRejectFormCtrl',
			templateUrl: '/public/app/concierge/contracts/contract-reject-form.html'
		};
	})
 .controller('ContractRejectFormCtrl', function($rootScope, $scope, ConciergeContracts, ContractStates, Alerts) {
	 var contractRejectFormCtrl = this;

	 var defaultRejectParams = {
		 reason: '',
			 comments: '',
		 rating: null
	 };

	 angular.extend(contractRejectFormCtrl, {
		 showRejectForm: false,
		 rejectReasons: [
			 'The service is no longer needed',
			 'I\'m unhappy with the service I have received',
			 'Change of circumstance'
		 ],
		 rejectParams: angular.copy(defaultRejectParams),
		 cancelReject: function() {
			 $scope.showRejectForm = false;
		 },
		 changeReason: function(reason) {
			 contractRejectFormCtrl.rejectParams.reason = contractRejectFormCtrl.rejectParams.reason == reason ? '' : reason;
		 },
		 endContract: function(contract) {
			 if (contractRejectFormCtrl.rejectParams.reason == '') {
				 return;
			 }

			 contractRejectFormCtrl.submitted = true;
			 contractRejectFormCtrl.loadingReject = true;
			 contractRejectFormCtrl.rejectParams._id = contract._id;

			 var method = null;
			 if (contractRejectFormCtrl.contract.state === CONTRACT_STATES.PENDING)
				method = 'reject';
			 else if (contractRejectFormCtrl.contract.state === CONTRACT_STATES.ACTIVE)
				method = 'dispute';

			 ConciergeContracts[method](contractRejectFormCtrl.rejectParams).$promise
				 .then(function(res) {
					 if (res.status == 'OK') {
						 $scope.showRejectForm = false;

						 // Update ui to match rejected contract
						 // if contract is pending or was accepted goes to rejected by client
						 if (contractRejectFormCtrl.contract.state == ContractStates.PENDING
							 || contractRejectFormCtrl.contract.state == ContractStates.ACCEPTED) {
							 contractRejectFormCtrl.conciergeCarer ?
							 contractRejectFormCtrl.contract.state = ContractStates.REJECTED_BY_CARER
							 : contractRejectFormCtrl.contract.state = ContractStates.REJECTED_BY_CLIENT;
						 }
						 // if contract was active goes to disputed by client
						 else if (contractRejectFormCtrl.contract.state == ContractStates.ACTIVE) {
							 contractRejectFormCtrl.conciergeCarer ?
							 contractRejectFormCtrl.contract.state = ContractStates.DISPUTED_BY_CARER
							 : contractRejectFormCtrl.contract.state = ContractStates.DISPUTED_BY_CLIENT;
						 }

						 // update payments
						 //conciergeContractsItemCtrl.paymentData = {};

						 $rootScope.$emit('contractUpdated', contractRejectFormCtrl.contract);
					 }
				 })
				 .catch(function(err) {
					 console.error(err);
					 Alerts.error('Error rejecting request.');
				 })
				 .finally(function() {
					 contractRejectFormCtrl.loadingReject = false;
				 });
		 }
	 });


	 $scope.$watch('showRejectForm', function(newValue, oldValue) {
		 contractRejectFormCtrl.showRejectForm = newValue;
		 if(newValue) {
			 contractRejectFormCtrl.rejectParams = angular.copy(defaultRejectParams);
		 }
	 });
	 $scope.$watch('contract', function(newValue, oldValue) {
		 contractRejectFormCtrl.contract = newValue;
	 });
	 $scope.$watch('conciergeCarer', function(newValue, oldValue) {
		 contractRejectFormCtrl.conciergeCarer = newValue;
	 });
 });