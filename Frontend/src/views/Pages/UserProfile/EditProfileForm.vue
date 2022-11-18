<template>
  <card>
    <b-row align-v="center" slot="header">
      <b-col cols="8">
        <h3 class="mb-0">Edit profile</h3>
      </b-col>
      <b-col cols="4" class="text-right">
        <a @click="updateProfile" class="btn btn-sm btn-primary">Settings</a>
      </b-col>
    </b-row>

    <b-form>
      <h6 class="heading-small text-muted mb-4">User information</h6>

      <div class="pl-lg-4">
        <b-row>
          <b-col lg="6">
            <base-input
              type="text"
              label="ID"
              placeholder="ID"
              v-model="user.userid"
            >
            </base-input>
          </b-col>
          <b-col lg="6">
            <base-input
              type="email"
              label="Email address"
              placeholder="mike@email.com"
              v-model="user.email"
            >
            </base-input>
          </b-col>
        </b-row>
        <b-row>
          <b-col lg="6">
            <base-input
              type="text"
              label="Name"
              placeholder="Name"
              v-model="user.username"
            >
            </base-input>
          </b-col>

        </b-row>
      </div>

      <!-- <hr class="my-4" /> -->
      <!-- Address -->
      <!-- <h6 class="heading-small text-muted mb-4">Contact information</h6>

      <div class="pl-lg-4">
        <b-row>
          <b-col md="12">
            <base-input
              type="text"
              label="Address"
              placeholder="Home Address"
              v-model="user.address"
            >
            </base-input>
          </b-col>
        </b-row>
        <b-row>
          <b-col lg="4">
            <base-input
              type="text"
              label="City"
              placeholder="City"
              v-model="user.city"
            >
            </base-input>
          </b-col>
          <b-col lg="4">
            <base-input
              type="text"
              label="Country"
              placeholder="Country"
              v-model="user.country"
            >
            </base-input>
          </b-col>
          <b-col lg="4">
            <base-input
              label="Postal Code"
              placeholder="ZIP Code"
              v-model="user.postalCode"
            >
            </base-input>
          </b-col>
        </b-row>
      </div> -->

      <hr class="my-4" />
      <!-- Description -->
      <h6 class="heading-small text-muted mb-4">About me</h6>
      <div class="pl-lg-4">
        <b-form-group
          label="About Me"
          label-class="form-control-label"
          class="mb-0"
          label-for="about-form-textaria"
        >
          <!--  <label class="form-control-label">About Me</label> -->
          <b-form-textarea
            rows="4"
            value="A beautiful premium dashboard for BootstrapVue."
            id="about-form-textaria"
            placeholder="A few words about you ..."
          ></b-form-textarea>
        </b-form-group>
      </div>
    </b-form>
  </card>
</template>
<script>
import { mapGetters } from "vuex";
import { modifyMember } from "@/api/memberApi"

export default {
  data() {
    return {
      user: {
        userid: "",
        username:"",
        useremail:"",
        userpwd:"",
        joindate:"",
      },
    };
  },
  computed: {
    ...mapGetters("member", ["checkUserInfo"]),
  },
  mounted() {
    this.user = {...this.checkUserInfo};
  },
  methods: {
    updateProfile() {
      console.log(this.user)
      modifyMember(this.user).then(() => {

      }).catch(() => {
        alert("회원정보 수정을 하지못했습니다.")
      })
    },
  },
};
</script>
<style></style>
