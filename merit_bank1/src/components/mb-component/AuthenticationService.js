import axios from 'axios';
import { API_URL } from '../../Constants';

export const USER_NAME_SESSION_ATTRIBUTE_NAME = 'authenticatedUser'


class AuthenticationService {
    
    // executeBasicAuthenticationService(username, password) {
    //     //let basicAuthHeader = 'Basic ' + window.btoa(username + ":" + password)
    //     return axios.get('http://localhost:8080/basicauth',
    //         {
    //             headers : {
    //                 authorization : this.createBasicAuthToken(username, password)
    //             }
    //         }
    //     )
    // }


    
    executeJwtAuthenticationService(username, password) {
        return axios.post(`${API_URL}/authenticate`,
        {
            username : username,
            password : password
        })
    }
    
    // createBasicAuthToken(username, password){
    //     return 'Basic ' + window.btoa(username + ":" + password)
    // }
    
    registerSuccessfulLoginForJwt(username, token) {
        sessionStorage.setItem(USER_NAME_SESSION_ATTRIBUTE_NAME, username)
        this.setupAxiosInterceptors(this.createJwtToken(token))
    }

    createJwtToken(token){
        return 'Bearer ' + token
    }

    // registerSuccessfulLogin(username, password){
      
    //     //let basicAuthHeader = 'Basic ' + window.btoa(username + ":" + password)
       
    //     console.log('registerSuccessfulLogin');
    //     sessionStorage.setItem('authenticatedUser', username);
    //     this.setupAxiosInterceptors(this.createBasicAuthToken(username, password))
    // }

    logout(){
        sessionStorage.removeItem(USER_NAME_SESSION_ATTRIBUTE_NAME);
    }

    isUserLoggedIn() {
        let user = sessionStorage.getItem(USER_NAME_SESSION_ATTRIBUTE_NAME);
        if(user===null) return false;
        return true;
    }

    getLoggedInUsername(){
        return sessionStorage.getItem(USER_NAME_SESSION_ATTRIBUTE_NAME);
    }

    setupAxiosInterceptors(token) {

       
        axios.interceptors.request.use(
            (config) => {
                if(this.isUserLoggedIn()){
                    config.headers.authorization = token
                }
                return config
            }
        )
    }
}

export default new AuthenticationService();