<template>
<div class="Main">

     <div :class="{'m-display-none':focusMode}"  class="Slide">
    <SlideCard></SlideCard>
    </div>
    <div :class="{'m-display-none':focusMode}" class="Notification">
      <RightCard></RightCard>
    </div>
    <div :class="{'m-display-none':focusMode}" class="Calendar">
    <Calendar></Calendar>
    </div>

  </div>
</template>

<script>
import SlideCard from "@/components/SlideCard";
import Calendar from "@/components/Calendar";
import RightCard from "@/components/RightCard";

export default {
  name: "Homepage",
  components: {Calendar, SlideCard,RightCard},
  data() {
    return {
      focusMode: false,
    }
  }
}
</script>


<style scoped>
/*.Calendar{*/
/*  right: 10px;*/
/*  top:10px;*/
/*}*/
.Slide{
  padding: 0;
  margin-left: 100px;
  margin-top: 10px;
  /*position: absolute;*/
  /*margin-top: 50px;*/
}
.Notification{
  padding: 0;
  margin-left: 500px;
  /*margin-right: 60px;*/

}
.Main{
  background: url("../assets/test1.jpg") no-repeat;
  background-size: cover;
  position: absolute;
  width: 100%;
  height: 100%;
  top: 0px;
  bottom: 0;
  right: 0px;
  overflow-y: auto;
  overflow-x: hidden;
}



</style>
