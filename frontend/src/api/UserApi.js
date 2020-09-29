
const axios = require('axios');
const hostname = 'localhost';
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

const requestTest = (data,callback,errorCallback) => {
  axios({
      method: 'get',
      url: BASE_URL + '/summoner',
      params:{
        summonerName: '바이오어'
      }
      })
      .then(function(response){
        callback(response);
      })
      .catch(function(error){
        errorCallback();
      });

}

const UserApi = {
    requestLogin:(data,callback,errorCallback)=>requestLogin(data,callback,errorCallback),
    requestTest:(data,callback,errorCallback)=>requestTest(data,callback,errorCallback),
}

export default UserApi

