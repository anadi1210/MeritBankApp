import axios from 'axios';
import { API_URL } from '../../Constants';

class MertiBankService {

    retrieveAccountHolder() {
        //console.log('executed service')

        return axios.get(`${API_URL}/AccountHolders`)
    }

    deleteAccountHolder() {
        //console.log('executed service')

        return axios.get(`${API_URL}/AccountHolders`)
    }
}

export default new MertiBankService()