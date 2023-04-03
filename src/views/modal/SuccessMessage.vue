<template>
    <div class="success-message">
        {{ message }}
    </div>
</template>
  
<script>
export default {
    props: {
        message: String
    }
};
</script>
  
<style scoped>
.success-message {
    position: fixed;
    top: 20px;
    right: -200px;
    padding: 10px 20px;
    border-radius: 5px;
    background-color: #67C23A;
    color: #fff;
    transition: all .4s ease-in-out;
}

.success-message.show {
    right: 20px;
}
</style>
  