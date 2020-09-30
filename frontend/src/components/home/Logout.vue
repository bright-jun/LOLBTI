<template>
    <v-list-item-title @click="logout">로그아웃</v-list-item-title>
</template>
<script>
export default {
    methods:{
        logout(){
            this.$session.remove("userinfo")
            this.$store.dispatch("logout")
            this.$router.push('/').catch(()=>{});
        }   
    }
}
</script>
