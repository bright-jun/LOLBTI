const axios = require("axios");
const hostname = "localhost";
const BASE_URL = "http://" + hostname + ":8080";

const requestLogin = (data, callback, errorCallback) => {
  axios({
    method: "post",
    url: BASE_URL + "/account/login",
    data: {
      email: data.email,
      password: data.password,
    },
  })
    .then(function(response) {})
    .catch(function(error) {
      errorCallback();
    });
};

const requestMbtiInfo = (summonerName, callback, errorCallback) => {
  axios({
    method: "get",
    url: BASE_URL + "/account/user/searchmbti",
    params: {
      summonerName: summonerName,
    },
  })
    .then(function(response) {
      // console.log(response);
      callback(response);
    })
    .catch(function(error) {
      errorCallback(error);
    });
};

const requestUserGameInfo = (summonerName, callback, errorCallback) => {
  axios({
    method: "get",
    url: BASE_URL + "/summoner",
    params: {
      summonerName: summonerName,
    },
  })
    .then(function(response) {
      // console.log(response);
      callback(response);
    })
    .catch(function(error) {
      errorCallback(error);
    });
};

const UserApi = {
  requestLogin: (data, callback, errorCallback) =>
    requestLogin(data, callback, errorCallback),

  requestMbtiInfo: (summonerName, callback, errorCallback) =>
    requestMbtiInfo(summonerName, callback, errorCallback),

  requestUserGameInfo: (summonerName, callback, errorCallback) =>
    requestUserGameInfo(summonerName, callback, errorCallback),
};

export default UserApi;
