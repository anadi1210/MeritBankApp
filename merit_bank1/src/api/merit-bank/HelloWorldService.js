import axios from 'axios';

class HelloWorldService {
    executeHelloWorldService() {
        //console.log('executed service')

        return axios.get('http://localhost:8080/hello')

    }

    executeHelloWorldPathVariableService(name) {
        //console.log('executed service')

        return axios.get(`http://localhost:8080/hello-bean/variable/${name}`)

    }
}

export default new HelloWorldService()