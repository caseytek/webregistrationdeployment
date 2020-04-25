<template>
  <div id="registration">
    <div id="headline">
      <p>
        Interested in getting updates about new content? Sign up today and never
        miss a beat.
      </p>
    </div>
    <div class="form-style-2">
      <div class="form-style-2-heading">Provide your information</div>
      <form id="app" @submit="checkForm" novalidate="true">
        <!-- if the errors array isn't empty, display errors -->
        <span v-if="errors.length">
          <b>Please correct the following error(s):</b>
        </span>
        <div class="error-list">
          <ul>
            <li v-for="error in errors" :key="error">
              <i class="fas fa-exclamation-circle"></i>
              {{ error }}
            </li>
          </ul>
        </div>

        <!-- if we don't get an ok response from the API, show this message, it is most 
        likely due to a duplicate email -->
        <p v-if="emailInvalid">
          Oops! It looks like there was a problem registering you. Try entering
          an email that isn't already in our system.
        </p>
        <label for="field1">
          <span>First Name <span class="required">*</span></span>
          <input
            type="text"
            class="input-field"
            name="field1"
            v-model.trim="user.firstName"
          />
        </label>
        <label for="field2">
          <span>Last Name</span>
          <input
            type="text"
            class="input-field"
            name="field2"
            v-model.trim="user.lastName"
          />
        </label>
        <label for="field3"
          ><span>Email <span class="required">*</span></span
          ><input
            type="email"
            class="input-field"
            name="field3"
            v-model.trim="user.email"
        /></label>
        <label
          ><span>Telephone</span
          ><input
            class="input-field"
            name="field4"
            id="phoneNumber"
            v-model="user.phoneNumber"
            type="tel"
            placeholder="xxx-xxx-xxxx"
        /></label>
        <label><span> </span><input type="submit" value="Sign Me Up!"/></label>
      </form>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      errors: [],
      emailInvalid: false,
      user: {
        firstName: "",
        lastName: "",
        email: "",
        phoneNumber: ""
      }
    };
  },
  methods: {
    checkForm(e) {
      this.errors = [];
      //if the user didn't fill out the first name field, add an error
      if (!this.user.firstName) {
        this.errors.push("First name required.");
      }
      //checks if user entered an email
      if (!this.user.email) {
        this.errors.push("Email required.");
        //checks if user entered an invalid email
      } else if (!this.validEmail(this.user.email)) {
        this.errors.push("Valid email required.");
      }
      //if the phone number is filled in, it is compared to a regex to make sure it
      // is in the right format
      if (
        !this.validTelephone(this.user.phoneNumber) &&
        this.user.phoneNumber != ""
      ) {
        this.errors.push("Enter a phone number in xxx-xxx-xxxx format");
      }

      //  if no errors, try the fetch statement
      if (!this.errors.length) {
        this.addUser();
      }

      e.preventDefault();
    },
    //If they fill in a phone number, validates by comparing it to a regex
    validTelephone(phoneNumber) {
      var re = /^[2-9]\d{2}-\d{3}-\d{4}$/;
      return re.test(phoneNumber);
    },
    //validates email with a regex, compares the regex and email for a match
    validEmail(email) {
      var re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
      return re.test(email);
    },
    addUser() {
      fetch("http://localhost:8080/webregistrationform/newUser", {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify(this.user)
      })
        .then(response => {
          //is status code successful, redirect to confirmation page
          if (response.ok) {
            this.$router.push("/Success");
          } else {
            //else, show invalid email message to user
            this.emailInvalid = true;
          }
        })
        .catch(err => {
          console.error(err + "problem adding user");
        });
    }
  }
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Yantramanav&display=swap");
#headline {
  width: 75%;
  margin: 0 10% 0 10%;
  height: 75px;
  background: #466a46;
  padding: 7px;
  border: 2px solid white;
}
#headline p {
  margin: 10px 0 10px 0;
  color: white;
  font-size: 18px;
}
.error-list {
  text-align: center;
  font-family: "Crimson Text", serif;
}
ul {
  list-style-type: none;
  color: red;
}
.form-style-2 .tel-number-field {
  width: 40px;
  text-align: center;
}
p,
b {
  font-size: 18px;
  font-family: "Crimson Text", serif;
}
#registration {
  opacity: 0.95;
  background-color: #ddeec4;
  margin: 30px 30% 30px 30%;
  padding: 20px;
  border: 1px solid #466a46;
  font-family: "Crimson Text", serif;
}
.form-style-2 {
  padding: 0px 10px 0px 10px;
}
.form-style-2-heading {
  font-weight: bold;
  font-style: italic;
  border-bottom: 2px solid #466a46;
  font-size: 18px;
  font-family: "Crimson Text", serif;
  padding-bottom: 3px;
}
.form-style-2 label {
  font-family: "Crimson Text", serif;
  display: block;
  margin: 0px 0px 10px 0px;
}
.form-style-2 label > span {
  width: 100px;
  font-weight: bold;
  float: left;
  padding-top: 8px;
  padding-right: 5px;
}
.form-style-2 span.required {
  color: red;
}

.form-style-2 input.input-field {
  min-width: 100px;
  width: 48%;
}
.form-style-2 input.input-field {
  border: 1px solid #c2c2c2;
  box-shadow: 1px 1px 4px #ebebeb;
  -moz-box-shadow: 1px 1px 4px #ebebeb;
  -webkit-box-shadow: 1px 1px 4px #ebebeb;
  border-radius: 3px;
  -webkit-border-radius: 3px;
  -moz-border-radius: 3px;
  padding: 7px;
  outline: none;
}
.form-style-2 .input-field:focus {
  border: 1px solid #0c0;
}
.form-style-2 .textarea-field {
  height: 100px;
  width: 55%;
}
.form-style-2 input[type="submit"],
.form-style-2 input[type="button"] {
  border: none;
  padding: 8px 15px 8px 15px;
  background: #ff8500;
  color: #fff;
  box-shadow: 1px 1px 4px #dadada;
  -moz-box-shadow: 1px 1px 4px #dadada;
  -webkit-box-shadow: 1px 1px 4px #dadada;
  border-radius: 3px;
  -webkit-border-radius: 3px;
  -moz-border-radius: 3px;
}
.form-style-2 input[type="submit"]:hover,
.form-style-2 input[type="button"]:hover {
  background: #ea7b00;
  color: #fff;
}

@media only screen and (max-width: 800px) {
  #registration {
    margin: 10%;
  }
}
@media only screen and (max-width: 400px) {
  #registration {
    margin: 10px 0% 0% 0%;
  }
  #headline p {
    margin: 5px 0 5px 0;
    font-size: 16px;
  }
}
@media only screen and (max-width: 375px) {
  #registration {
    margin: 5px 0% 0% 0%;
  }
  #headline p {
    margin: 5px 0 5px 0;
    font-size: 15px;
  }
}
</style>
