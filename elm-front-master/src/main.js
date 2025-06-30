import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import axios from 'axios';
import qs from 'qs';
import {
    getCurDate,
    setSessionStorage,
    getSessionStorage,
    removeSessionStorage,
    setLocalStorage,
    getLocalStorage,
    removeLocalStorage
} from './common.js';

// 设置 axios 的基础 URL 部分
axios.defaults.baseURL = 'http://localhost:9906/';

const app = createApp(App);

// 将 axios, qs 和其他方法挂载到全局
app.config.globalProperties.$axios = axios;
app.config.globalProperties.$qs = qs;
app.config.globalProperties.$getCurDate = getCurDate;
app.config.globalProperties.$setSessionStorage = setSessionStorage;
app.config.globalProperties.$getSessionStorage = getSessionStorage;
app.config.globalProperties.$removeSessionStorage = removeSessionStorage;
app.config.globalProperties.$setLocalStorage = setLocalStorage;
app.config.globalProperties.$getLocalStorage = getLocalStorage;
app.config.globalProperties.$removeLocalStorage = removeLocalStorage;

// 路由守卫
router.beforeEach((to, from, next) => {
    let user = sessionStorage.getItem('user');
    // 除了登录、注册、首页、商家列表、商家信息之外，都需要判断是否登录
    if (!(to.path === '/' || to.path === '/index' || to.path === '/businessList' || to.path === '/businessInfo' || to.path === '/login' || to.path === '/register')) {
        if (user == null) {
            next('/login');
        } else {
            next();
        }
    } else {
        next();
    }
});

// 挂载应用
app.use(router);
app.mount('#app');
