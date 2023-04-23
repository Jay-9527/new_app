<script>
export default {
    props: {
        message: String
    }
}
</script>
<template>
  <!-- 首页展示 -->
  <div class="container">
    <div class="row">
      <div class="col-md-12 d-md-flex justify-content-center align-items-center flex-wrap">
        <div class="box">
          <img class="boox" src="../../../image/welcome.svg" alt="welcome page">
          <h4>欢迎{{message}}，登录成功系统！</h4>
        </div>
      </div>
    </div>
  </div>
</template>
<style>
.boox{
    margin: 0;
    padding: 0;
    max-width: 400px;
    max-height: 600px;
    background-color: white;
}
.box{
  margin: 120px auto;
  text-align: center;
  border: thick double silver;
  box-shadow: 0 2px 2px 2px silver;
}

</style>