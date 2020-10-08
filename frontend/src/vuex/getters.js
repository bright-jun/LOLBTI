export default {
  getChampNameByNo: (state) => (no) => {
    return state.champ.find((cham) => cham.key === no).name;
  },
  getChampIdByNo: (state) => (no) => {
    return state.champ.find((cham) => cham.key === no).id;
  },
  getSummonerWinLose: (state) => () => {
    return state.summonerWinLose;
  },
  getChampNameById: (state) => (id) => {
    return state.champ.find((cham) => cham.id === id).name;
  },
  getChampKeyById: (state) => (id) => {
    return state.champ.find((cham) => cham.id === id).key;
  },
  getChampIdByName: (state) => (name) => {
    return state.champ.find((cham) => cham.name === name).id;
  },
  getChampKeyByName: (state) => (name) => {
    return state.champ.find((cham) => cham.name === name).key;
  },
};
