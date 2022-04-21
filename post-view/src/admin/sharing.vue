<template>
  <el-container>
    <el-header height="350px">
      <slide></slide>
      <p></p>

      <el-divider class="el-divider--horizontal"></el-divider>
    </el-header>
    <el-container>
      <el-aside width="300px" align="right">
        <div class="card">

          <div class="card-header">

            <p style="margin-top: 0px;">Programming tools</p>

          </div>

          <div class="card-footer">
            <el-collapse>
              <el-collapse-item name="1" style="padding-left: 20px;padding-right: 20px" title="Common compilers">
                <div align="left"><el-link href="https://www.jetbrains.com/" target="_blank">IDEA</el-link> </div>
                <div align="left"><el-link href="https://netbeans.apache.org//" target="_blank">Netbeans</el-link> </div>
                <div align="left"><el-link href="https://www.eclipse.org/downloads/" target="_blank">Eclipse</el-link> </div>
                <div align="left"><el-link href="https://visualstudio.microsoft.com/zh-hans/" target="_blank">Visual Studio</el-link> </div>
                <div align="left"><el-link href="https://code.visualstudio.com/" target="_blank">Vs code</el-link> </div>
              </el-collapse-item>
              <el-collapse-item name="2" style="padding-left: 20px;padding-right: 20px" title="Database">
                <div align="left"><el-link href="https://www.navicat.com.cn/" target="_blank">Navicat premium</el-link> </div>
                <div align="left"><el-link href="https://www.apachefriends.org/index.html" target="_blank">XAMPP</el-link> </div>
                <div align="left"><el-link href="https://www.oracle.com/technetwork/testcontent/111060-win32soft-084328-zhs.html" target="_blank">Oracle database</el-link> </div>
              </el-collapse-item>
              <el-collapse-item name="3" style="padding-left: 20px;padding-right: 20px" title="Virtual machine">
                <div align="left"><el-link href="https://www.virtualbox.org/" target="_blank">Virtual Box</el-link> </div>
                <div align="left"><el-link href="https://www.vmware.com/" target="_blank">VMware</el-link> </div>
                <div align="left"><el-link href="https://www.parallels.cn/pd/general/" target="_blank">Parallels Desktop</el-link> </div>
              </el-collapse-item>
              <el-collapse-item name="4" style="padding-left: 20px;padding-right: 20px" title="Website development tools">
                <div align="left"><el-link href="http://www.sublimetext.com/" target="_blank">Sublime</el-link> </div>
                <div align="left"><el-link href="https://www.jetbrains.com/webstorm/" target="_blank">Webstorm</el-link> </div>
                <div align="left"><el-link href="https://www.adobe.com/cn/products/dreamweaver.html" target="_blank">DreamWeaver</el-link> </div>
              </el-collapse-item>
            </el-collapse>
          </div>
        </div>

      </el-aside>
      <el-main>
        <h2 style="font-family: 楷体">Teaching video</h2>
        <hr>
        <el-tabs type="border-card">
          <el-tab-pane label="Java">
            <div>
              <iframe src="//player.bilibili.com/player.html?aid=68373450&bvid=BV12J41137hu&cid=118499718&page=1" scrolling="no" border="0" frameborder="no" framespacing="0" allowfullscreen="true"> </iframe>
              <div><el-link href="https://www.bilibili.com/video/BV12J41137hu?share_source=copy_web" target="_blank" >Java零基础学习视频通俗易懂</el-link> </div>
            </div>
            <div>
              <iframe src="//player.bilibili.com/player.html?aid=54171171&bvid=BV1V4411p7EF&cid=94743823&page=1" scrolling="no" border="0" frameborder="no" framespacing="0" allowfullscreen="true"> </iframe>
              <div><el-link href="https://www.bilibili.com/video/BV1V4411p7EF?share_source=copy_web" target="_blank" >多线程详解</el-link> </div>
            </div>
            <div>
              <iframe src="//player.bilibili.com/player.html?aid=55440782&bvid=BV1p4411P7V3&cid=96929343&page=1" scrolling="no" border="0" frameborder="no" framespacing="0" allowfullscreen="true"> </iframe>
              <div><el-link href="https://www.bilibili.com/video/BV1p4411P7V3?share_source=copy_web" target="_blank" >注解和反射</el-link> </div>
            </div>
            <div>
              <iframe src="//player.bilibili.com/player.html?aid=78501130&bvid=BV1MJ411v7tJ&cid=134314948&page=1" scrolling="no" border="0" frameborder="no" framespacing="0" allowfullscreen="true"> </iframe>
              <div><el-link href="https://www.bilibili.com/video/BV1MJ411v7tJ?share_source=copy_web" target="_blank" >JavaSE阶段回顾总结</el-link> </div>
            </div>

          </el-tab-pane>
          <el-tab-pane label="Python">
            <div>
              <iframe src="//player.bilibili.com/player.html?aid=14184325&bvid=BV1ex411x7Em&cid=23153678&page=1" scrolling="no" border="0" frameborder="no" framespacing="0" allowfullscreen="true"> </iframe>
              <div><el-link href="https://www.bilibili.com/video/BV1ex411x7Em?share_source=copy_web" target="_blank" >黑马程序员Python教程</el-link> </div>
            </div>
            <div>
              <iframe src="//player.bilibili.com/player.html?aid=379745145&bvid=BV1YZ4y197RY&cid=457009666&page=1" scrolling="no" border="0" frameborder="no" framespacing="0" allowfullscreen="true"> </iframe>
              <div><el-link href="https://www.bilibili.com/video/BV1YZ4y197RY?share_source=copy_web" target="_blank" >Python全栈教程</el-link> </div>
            </div>
            <div>
              <iframe src="//player.bilibili.com/player.html?aid=213200844&bvid=BV1wa411v7KX&cid=577293048&page=1" scrolling="no" border="0" frameborder="no" framespacing="0" allowfullscreen="true"> </iframe>
              <div><el-link href="https://www.bilibili.com/video/BV1wa411v7KX?share_source=copy_web" target="_blank" >2022最新python全套教程</el-link> </div>
            </div>
            <div>
              <iframe src="//player.bilibili.com/player.html?aid=297834004&bvid=BV1JF41137Bu&cid=565099012&page=1" scrolling="no" border="0" frameborder="no" framespacing="0" allowfullscreen="true"> </iframe>
              <div><el-link href="https://www.bilibili.com/video/BV1JF41137Bu?share_source=copy_web" target="_blank" >21天从Python负基础到精通教程</el-link> </div>
            </div>
          </el-tab-pane>
          <el-tab-pane label="Front-end">
            <div>
              <iframe src="//player.bilibili.com/player.html?aid=55631961&bvid=BV1x4411V75C&cid=97256834&page=1" scrolling="no" border="0" frameborder="no" framespacing="0" allowfullscreen="true"> </iframe>
              <div><el-link href="https://www.bilibili.com/video/BV1x4411V75C?share_source=copy_web" target="_blank" >HTML5通俗易懂</el-link> </div>
            </div>
            <div>
              <iframe src="//player.bilibili.com/player.html?aid=81109044&bvid=BV1YJ411a7dy&cid=138807880&page=1" scrolling="no" border="0" frameborder="no" framespacing="0" allowfullscreen="true"> </iframe>
              <div><el-link href="https://www.bilibili.com/video/BV1YJ411a7dy?share_source=copy_web" target="_blank" >CSS3最新教程快速入门通俗易懂</el-link> </div>
            </div>
            <div>
              <iframe src="//player.bilibili.com/player.html?aid=82042680&bvid=BV1JJ41177di&cid=140371738&page=1" scrolling="no" border="0" frameborder="no" framespacing="0" allowfullscreen="true"> </iframe>
              <div><el-link href="https://www.bilibili.com/video/BV1JJ41177di?share_source=copy_web" target="_blank" >JavaScript最新教程通俗易懂</el-link> </div>
            </div>
            <div>
              <iframe src="//player.bilibili.com/player.html?aid=73489017&bvid=BV18E411a7mC&cid=125708387&page=1" scrolling="no" border="0" frameborder="no" framespacing="0" allowfullscreen="true"> </iframe>
              <div><el-link href="https://www.bilibili.com/video/BV18E411a7mC?share_source=copy_web" target="_blank" >Vue最新快速上手教程通俗易懂</el-link> </div>
            </div>
          </el-tab-pane>
          <el-tab-pane label="Back-end">
            <div>
              <iframe src="//player.bilibili.com/player.html?aid=71110355&bvid=BV1WE411d7Dv&cid=123206878&page=1" scrolling="no" border="0" frameborder="no" framespacing="0" allowfullscreen="true"> </iframe>
              <div><el-link href="https://www.bilibili.com/video/BV1WE411d7Dv?share_source=copy_web" target="_blank" >Spring5最新完整教程IDEA版通俗易懂</el-link> </div>
            </div>
            <div>
              <iframe src="//player.bilibili.com/player.html?aid=71874024&bvid=BV1aE41167Tu&cid=124535678&page=1" scrolling="no" border="0" frameborder="no" framespacing="0" allowfullscreen="true"> </iframe>
              <div><el-link href="https://www.bilibili.com/video/BV1aE41167Tu?share_source=copy_web" target="_blank" >SpringMVC最新教程IDEA版通俗易懂</el-link> </div>
            </div>
            <div>
              <iframe src="//player.bilibili.com/player.html?aid=75233634&bvid=BV1PE411i7CV&cid=128676240&page=1" scrolling="no" border="0" frameborder="no" framespacing="0" allowfullscreen="true"> </iframe>
              <div><el-link href="https://www.bilibili.com/video/BV1PE411i7CV?share_source=copy_web" target="_blank" >SpringBoot最新教程IDEA版通俗易懂</el-link> </div>
            </div>
            <div>
              <iframe src="//player.bilibili.com/player.html?aid=69742084&bvid=BV1NE411Q7Nx&cid=120850630&page=1" scrolling="no" border="0" frameborder="no" framespacing="0" allowfullscreen="true"> </iframe>
              <div><el-link href="https://www.bilibili.com/video/BV1NE411Q7Nx?share_source=copy_web" target="_blank" >Mybatis最新完整教程IDEA版通俗易懂</el-link> </div>
            </div>
          </el-tab-pane>
        </el-tabs>
      </el-main>
    </el-container>
  </el-container>



</template>

<script>

import slide from "@/components/slide";

export default {
  name: "sharing",
  components: {slide},
  data(){
    return{
      activeName: '1',



    }

  }

}
</script>

<style scoped>
.card {
  width: 250px;
  padding: 20px 0px 0px 0px;
  background-color: white;
}
.card-header {
  text-align: center;
}
.card-footer {
  margin-top: 10px;
}
.card:hover {
  -webkit-box-shadow: #ccc 0px 10px 10px;
  -moz-box-shadow: #ccc 0px 10px 10px;
  box-shadow: #ccc 0px 10px 10px;
}
.el-divider--horizontal{
  background-color: purple;
  font-weight: bold;
  height: 5px;
  /*margin: 2em;*/
}


</style>
