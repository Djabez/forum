<template>
  <el-carousel :interval="4000" type="card" height="300px">
    <el-carousel-item v-for="item in imgList" :key="item">
      <el-link :href="item.source" target="_blank">
        <el-image :src="item.imgUrl"  ></el-image>
      </el-link>

    </el-carousel-item>
  </el-carousel>

</template>

<script>
export default {
  data(){
    return{
      imgList: [
        { id:1,source: 'https://www.csdn.net/', imgUrl: require('../assets/1.jpg') },
        { id:2,source: 'https://www.runoob.com/', imgUrl: require('../assets/5.jpg') },
        { id:3,source: 'https://www.oschina.net/', imgUrl: require('../assets/3.jpg') },
        { id:4,source: 'https://leetcode-cn.com/', imgUrl: require('../assets/4.jpg') }
      ]

    }
  }

}
</script>

<style>
.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 200px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n+1) {
  background-color: #d3dce6;
}

</style>
