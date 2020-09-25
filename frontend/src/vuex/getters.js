export default {
  getChampNameByNo: (state) => (no) => {
    return state.champ.find((cham) => cham.key === no).name;
  },
};
