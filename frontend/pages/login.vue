<template>
  <section class="section">
    <div class="container">
      <div class="columns">
        <div class="column is-4 is-offset-4">
          <h2 class="title has-text-centered">Witaj z powrotem!</h2>

          <Notification :message="error" v-if="error"/>
          <form>
            <v-text-field
              v-model="email"
              label="E-mail"
              :rules="[rules.required, rules.email]"
            ></v-text-field>
            <v-text-field
              v-model="password"
              :append-icon="show ? 'mdi-eye' : 'mdi-eye-off'"
              :rules="[rules.required]"
              :type="show ? 'text' : 'password'"
              name="input-10-1"
              label="Hasło"
              counter
              @click:append="show = !show"
            ></v-text-field>
            <v-btn
              class="mr-4"
              @click="login"
            >
              Zaloguj!
            </v-btn>
            <v-btn @click="clear">
              Wyczyść
            </v-btn>

            <div class="has-text-centered" style="margin-top: 20px">
              <p>
                Nie posiadasz konta? Załóż je! <nuxt-link to="/register">Rejestracja</nuxt-link>
              </p>
            </div>
          </form>


        </div>
      </div>
    </div>
  </section>
</template>

<script>
import Notification from '~/components/Notification'

export default {
  components: {
    Notification,
  },

  data() {
    return {
      email: '',
      password: '',
      show: false,
      error: null,
      rules: {
        required: value => !!value || 'Required.',
        email: value => {
          const pattern = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
          return pattern.test(value) || 'Nieprawidłowy adres email.'
        },
      },
    }
  },

  methods: {
    async login() {
      try {
        await this.$auth.loginWith('local', {
          data: {
            email: this.email,
            password: this.password
          }
        })

        this.$router.push('/')
      } catch (e) {
        this.error = e.response.data.message
      }
    }
  }
}
</script>
