<template>
    <div class="IndexPage">
        <header>
            <nav class="navbar bg-body-tertiary  ">
                <div class="container-fluid">
                    <a class="navbar-brand">
                        <img src="https://img.icons8.com/cotton/24/null/money--v2.png" class="d-inline-block align-text-top"
                            alt="Logo" />
                        Bootstrap
                    </a>
                    <form class="d-flex align-items-center" role="search">
                        <input class="form-control" id="Navin" type="search" placeholder="Search" aria-label="Search">
                        <button class="btn btn-outline-success" type="submit">Search</button>
                    </form>
                </div>
            </nav>
        </header>
        <div>

        </div>
    </div>
</template>

<script >
export default {
    name: 'App5Index',

    data() {
        return {
            name: "ssss"
        };
    },

    mounted() {

    },

    methods: {

    },
};
</script>

<style scoped>
#Navin {
    height: 40px;
}
</style>