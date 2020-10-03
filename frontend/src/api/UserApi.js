import store from "../vuex/store";

const axios = require("axios");
const hostname = "localhost:8080/api";
// const hostname = "j3a109.p.ssafy.io/api";
const BASE_URL = "https://" + hostname;

const requestLogin = (data, callback, errorCallback) => {
  axios({
    method: "post",
    url: BASE_URL + "/account/login",
    data: {
      id: data.email,
      password: data.password,
    },
  })
    .then(function(response) {
      if (response.data.status) {
        store.commit("login", {
          email: response.data.object.id,
          summonerName: response.data.object.summonerName,
        });
        callback();
      } else {
        errorCallback();
      }
    })
    .catch(function(error) {
      errorCallback();
    });
};

const requestJoin = (data, callback, errorCallback) => {
  axios({
    method: "post",
    url: BASE_URL + "/account/user/join",
    data: {
      id: data.email,
      password: data.password,
      summonerName: data.userId,
    },
    params: {
      mbti: data.userMbti,
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

const requestFreqChampList = (summonerName, callback, errorCallback) => {
  axios({
    method: "get",
    url: BASE_URL + "/summoner/freqchamp",
    params: {
      summonerName: summonerName,
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

const requestFreqLane = (summonerName, callback, errorCallback) => {
  axios({
    method: "get",
    url: BASE_URL + "/summoner/freqlane",
    params: {
      summonerName: summonerName,
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

const updateUserGameInfo = (summonerName, callback, errorCallback) => {
  axios({
    method: "get",
    url: BASE_URL + "/update/gamedata",
    params: {
      summonerName: summonerName,
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

const requestSummonerNameAndMbtiTypeById = (
  userid,
  callback,
  errorCallback
) => {
  axios({
    method: "get",
    url: BASE_URL + "/account/user/search",
    params: {
      id: userid,
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

const updateUserInfo = (summonerName, email, mbti, callback, errorCallback) => {
  axios({
    method: "put",
    url: BASE_URL + "/account/user/update",
    params: {
      id: email,
      summonerName: summonerName,
      mbti: mbti,
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

  requestJoin: (data, callback, errorCallback) =>
    requestJoin(data, callback, errorCallback),

  requestMbtiInfo: (summonerName, callback, errorCallback) =>
    requestMbtiInfo(summonerName, callback, errorCallback),

  requestUserGameInfo: (summonerName, callback, errorCallback) =>
    requestUserGameInfo(summonerName, callback, errorCallback),

  requestRecommendChampList: (summonerName, type, callback, errorCallback) =>
    requestRecommendChampList(summonerName, type, callback, errorCallback),

  requestFreqChampList: (summonerName, callback, errorCallback) =>
    requestFreqChampList(summonerName, callback, errorCallback),

  requestFreqLane: (summonerName, callback, errorCallback) =>
    requestFreqLane(summonerName, callback, errorCallback),

  updateUserGameInfo: (summonerName, callback, errorCallback) =>
    updateUserGameInfo(summonerName, callback, errorCallback),

  requestSummonerNameAndMbtiTypeById: (userid, callback, errorCallback) =>
    requestSummonerNameAndMbtiTypeById(userid, callback, errorCallback),

  updateUserInfo: (summonerName, email, mbti, callback, errorCallback) =>
    updateUserInfo(summonerName, email, mbti, callback, errorCallback),
};

export default UserApi;
