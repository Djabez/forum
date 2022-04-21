<template>
  <div id="Dynamic" :style="{width:fullWidth+'px',height:fullHeight+'px'}">
    <!-- Image carousel -->
    <div class="banner_div">
      <el-carousel :interval="interval" trigger="click" :height="bannerHeight+'px'">
        <el-carousel-item v-for="it in imgList" :key="it.id">
          <el-row :style="{width:fullWidth+'px',height:fullHeight+'px'}">
            <el-col :span="24">
              <img ref="bannerimg" :src="it.imgUrl" :alt="it.id" @load="imgLoad" :style="{width: fullWidth+'px',height:fullHeight+'px'}">
            </el-col>
          </el-row>
        </el-carousel-item>
      </el-carousel>
    </div>

  </div>
</template>

<script>
export default {
  data() {
    return {
      fullWidth:600,
      fullHeight:400,
      bannerHeight:"200",
      bannerWidth:"200",
      //interval time
      interval:4000,
      imgList: [
        { id: 1, imgUrl: require('../assets/logo-xjtlu.jpeg') },
        { id: 2, imgUrl: require('../assets/log.jpeg') },
        { id: 3, imgUrl: require('../assets/CB.jpeg') },
        { id: 4, imgUrl: require('../assets/IBSS.jpeg') }
      ]
    }
  },
  mounted(){

    this.imgLoad();

  },
  methods: {
    imgLoad(){
      console.log()
      this.$nextTick(()=>{
        let ref = this.$refs
        this.bannerHeight=ref.bannerimg && ref.bannerimg.length ? ref.bannerimg[0].height:'';
        this.bannerWidth=ref.bannerimg && ref.bannerimg.length ? ref.bannerimg[0].width:'';
      })
    },

  }
}
</script>

<style  scoped>
#Dynamic{
  /*width: 100%;*/
  /*height: 100%;*/
  /*overflow-x: hidden;*/
  /*.banner_div{*/
  /*  .el-row{*/
  /*    padding: 0;*/
  /*  }*/
  /*}*/
}
.banner_div{

}
</style>

