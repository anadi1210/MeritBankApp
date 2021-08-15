import axios from 'axios';

class HelloWorldService {
    executeHelloWorldService() {
        //console.log('executed service')

        return axios.get('http://localhost:8080/hello')

    }

    executeHelloWorldPathVariableService(name) {
        //console.log('executed service')

        // let username = 'anadi'
        // let password  = '123'

        // let basicAuthHeader = 'Basic ' + window.btoa(username + ":" + password)


        return axios.get(`http://localhost:8080/hello-bean/variable/${name}`
        // ,
        
        //     {
        //         headers : {
        //             authorization : basicAuthHeader
        //         }        
        //     }
        )

    }
}

export default new HelloWorldService()