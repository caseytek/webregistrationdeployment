<template>
  <div id="confirmation">
    <h1>Thanks for signing up!</h1>
  </div>
</template>
<style scoped>
#confirmation {
  opacity: 0.95;
  background-color: #ddeec4;
  margin: 30px 30% 30px 30%;
  padding: 20px;
  border: 1px solid #466a46;
  font-family: "Crimson Text", serif;
}
@media only screen and (max-width: 800px) {
  #confirmation {
    margin: 10%;
  }
}
@media only screen and (max-width: 400px) {
  #confirmation {
    margin: 10px 0% 0% 0%;
    font-size: 12px;
  }
}
</style>
