import {defineStore} from "pinia";

const useUserStore=defineStore('user',{
    state:()=>({
        user:null
    }),action:{
        login(user){
            this.user=user;
        },logout(){
            this.user=null
        }
    }
})