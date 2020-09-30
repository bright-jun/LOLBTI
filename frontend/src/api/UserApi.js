const axios = require("axios");
const hostname = "localhost:8080/api";
// const hostname = "j3a109.p.ssafy.io/api";
const BASE_URL = "http://" + hostname;

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

const requestRecommendChampList = (
  summonerName,
  type,
  callback,
  errorCallback
) => {
  axios({
    method: "get",
    url: BASE_URL + "/recommend/champion",
    params: {
      summonerName: summonerName,
      type: type,
    },
  })
    .then(function(response) {
      // console.log(response);
      callback(response);
    })
    .catch(function(error) {
      // console.log("error");
      errorCallback(error);
    });
};

const requestTest = (data, callback, errorCallback) => {
  axios({
    method: "get",
    url: BASE_URL + "/summoner",
    params: {
      summonerName: "바이오어",
    },
  })
    .then(function(response) {
      callback(response);
    })
    .catch(function(error) {
      errorCallback();
    });
};

const UserApi = {
  requestLogin: (data, callback, errorCallback) =>
    requestLogin(data, callback, errorCallback),

  requestMbtiInfo: (summonerName, callback, errorCallback) =>
    requestMbtiInfo(summonerName, callback, errorCallback),

  requestUserGameInfo: (summonerName, callback, errorCallback) =>
    requestUserGameInfo(summonerName, callback, errorCallback),

  requestRecommendChampList: (summonerName, callback, errorCallback) =>
    requestRecommendChampList(summonerName, callback, errorCallback),
};

export default UserApi;
