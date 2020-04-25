<template>
  <div id="app">
    <Title />
    <Footer />
    <router-view />
  </div>
</template>

<script>
import Footer from "@/components/Footer";
import Title from "@/components/Title";

export default {
  components: {
    Footer,
    Title
  }
};
</script>

<style>
@import url("https://fonts.googleapis.com/css2?family=Crimson+Text&display=swap");
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  text-align: center;
  padding: 20px;
}
body {
  background-image: url("~@/assets/leaves.jpg");
}
@media only screen and (max-width: 375px) {
  #app {
    padding: 5px;
  }
}
</style>
