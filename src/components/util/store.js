const state = {
    isLogin: false,
    successMessage: ''
};

const mutations = {
    login(state) {
        state.isLogin = true;
    },
    logout(state) {
        state.isLogin = false;
    },
    setSuccessMessage(state, message) {
        state.successMessage = message;
    }
};  