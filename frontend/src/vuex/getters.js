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
};
