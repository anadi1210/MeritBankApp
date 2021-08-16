import axios from 'axios';
import { API_URL } from '../../Constants';

class MertiBankService {

    retrieveAccountHolder(username) {
        console.log('executed retrieveAccountHolder')
        console.log('username is :: '+username)
        return axios.get(`${API_URL}/AccountHolders/${username}`)
    }

    createAccountHolder(accountHolder){
       return axios.post(`${API_URL}/AccountHolders`,accountHolder)     
    }

    createCheckingAccount(username, checkingAccount){
        
        return axios.post(`${API_URL}/AccountHolders/${username}/CheckingAccounts`,checkingAccount)
    }

    createSavingsAccount(username, savingsAccount){
        
        return axios.post(`${API_URL}/AccountHolders/${username}/SavingsAccounts`,savingsAccount)
    }

    createCDAccount(username, CDAccount) {
        return axios.post(`${API_URL}/AccountHolders/${username}/CDAccounts`,CDAccount)
    }

    processDeposit(username, depositTransaction){
        return axios.post(`${API_URL}/AccountHolders/${username}/transaction`,depositTransaction)
    }

    processWithdraw(username, withdrawTransaction){
        return axios.post(`${API_URL}/AccountHolders/${username}/transaction`,withdrawTransaction)
    }
    processTransfer(username, transferTransaction){
        return axios.post(`${API_URL}/AccountHolders/${username}/transaction`,transferTransaction)
    }

    deleteAccountHolder() {
        //console.log('executed service')

        return axios.get(`${API_URL}/AccountHolders`)
    }

}

export default new MertiBankService()