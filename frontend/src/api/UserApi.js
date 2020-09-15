
const axios = require('axios');
const hostname = '';
const BASE_URL = 'http://' + hostname + ':8080';

const requestLogin = (data,callback,errorCallback) => {
    axios({
        method: 'post',
        url: BASE_URL + '/account/login',
        data:{
          email: data.email,
          password: data.password
        }
        })
        .then(function(response){
        })
        .catch(function(error){
          errorCallback();
        });
  
}

const UserApi = {
    requestLogin:(data,callback,errorCallback)=>requestLogin(data,callback,errorCallback),
}

export default UserApi

