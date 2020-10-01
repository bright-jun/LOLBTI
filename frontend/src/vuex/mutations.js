export default {
    login: function(state, payload){
        state.isUser = true;
        state.email = payload.email;
        state.summonerName = payload.summonerName;
    },
    logout:function(state){
        state.isUser=false;
        state.email="";
        state.summonerName="";
    }

}