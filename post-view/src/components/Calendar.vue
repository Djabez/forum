<template>
  <!--Calendar-->
  <div class="blog-calendar">
    <el-calendar>
    </el-calendar>
  </div>
</template>

<script>
export default {
  name: "Calendar"
}
</script>

<style scoped>


.blog-calendar >>> .el-calendar div.el-calendar-day {
  height: 30px;
  font-size: 10px;


}
.blog-calendar >>> .el-calendar div.el-calendar__header {
  height: 30px;
  font-size: 12px;
  padding: 10px 10px 10px 10px;

}
.blog-calendar >>> .el-calendar div.el-calendar__body {
  padding: 10px 10px 10px 10px;
  font-size: 10px;
}
.blog-calendar {
  position:absolute;
  margin-top: -350px;
  width: 300px;
  text-align: center;
  margin-left: 900px;
}

.blog-calendar >>> .el-calendar div.el-calendar__title {
  padding: 5px 5px 5px 5px;
}

.blog-calendar >>> .el-calendar button.el-button--plain.el-button--mini {
  padding: 4px 4px 4px 4px;
  max-height: 20px;
  font-size: 12px;
}

.blog-calendar >>> .el-calendar div.el-calendar__button-group {
  height: 30px;
  margin-top: 6px;
}

</style>
