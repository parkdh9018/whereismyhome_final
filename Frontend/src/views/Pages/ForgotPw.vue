<template>
    <div>
      <!-- Header -->
      <div class="header bg-gradient-success py-7 py-lg-8 pt-lg-9">
        <b-container class="container">
          <div class="header-body text-center mb-7">
            <b-row class="justify-content-center">
              <b-col xl="5" lg="6" md="8" class="px-5">
                <h1 class="text-white">비밀번호 재설정</h1>
                <p class="text-lead text-white">새로운 비밀번호를 이메일로 보내드립니다. </p>
              </b-col>
            </b-row>
          </div>
        </b-container>
        <div class="separator separator-bottom separator-skew zindex-100">
          <svg x="0" y="0" viewBox="0 0 2560 100" preserveAspectRatio="none" version="1.1"
               xmlns="http://www.w3.org/2000/svg">
            <polygon class="fill-default" points="2560 0 2560 100 0 100"></polygon>
          </svg>
        </div>
      </div>
      <!-- Page content -->
      <b-container class="mt--8 pb-5">
        <!-- Table -->
        <b-row class="justify-content-center">
          <b-col lg="6" md="8" >
            <b-card no-body class="bg-secondary border-0">
              <b-card-body class="px-lg-5 py-lg-5">
                <validation-observer v-slot="{handleSubmit}" ref="formValidator">
                  <b-form v-if="!findCheck" role="form" @submit.prevent="handleSubmit(onSubmit)">
                    <base-input alternative
                                class="mb-3"
                                prepend-icon="ni ni-hat-3"
                                placeholder="ID"
                                name="ID"
                                :rules="{required: true}"
                                v-model="member.userid">
                    </base-input>
  
                    <base-input alternative
                                class="mb-3"
                                prepend-icon="ni ni-email-83"
                                placeholder="Email"
                                name="Email"
                                :rules="{required: true, email: true}"
                                v-model="member.email">
                    </base-input>
  
                    <span class="text-danger font-weight-500"
                    ><small>{{ errorMessage }} </small></span
                  >
                    <div class="text-center">
                      <b-button type="submit" variant="primary" class="mt-4">비밀번호 재설정</b-button>
                    </div>
                  </b-form>
                  <div v-else>
                    <p class="h1">메일이 전송되었습니다</p>
                    <p>3초 후 메인페이지로 이동합니다.</p>
                  </div>
                </validation-observer>
              </b-card-body>
            </b-card>
            <b-row class="mt-3">
            <b-col cols="6">
              <router-link to="/login" class="text-light"
                ><small>Login</small></router-link
              >
            </b-col>
            <b-col cols="6" class="text-right">
              <router-link to="/register" class="text-light"
                ><small>Create new account</small></router-link
              >
            </b-col>
          </b-row>
          </b-col>
        </b-row>
      </b-container>
    </div>
  </template>
  <script>
  import {passwordFind} from '@/api/memberApi';

    export default {
      name: 'register',
      data() {
        return {
          member: {
            userid: '',
            email: '',
          },
          errorMessage:"",
          findCheck: false,
        }
      },
      methods: {
        onSubmit() {
          passwordFind(this.member)
          .then((res) => {
            console.log(res);
            if(res.data == "fail") {
              this.errorMessage = "아이디와 이메일을 확인해주세요"
            } else {
              this.findCheck = true;
              setTimeout(() => {
                this.$router.push("/");
              }, 3000);
            }
          })
        }
      }
  
    };
  </script>
  <style></style>
  