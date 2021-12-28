<template>
  <section class="section">
    <div class="container">
      <div class="columns">
        <div class="column is-4 is-offset-4">
          <h2 class="title has-text-centered">Rejestracja nowego użytkownika</h2>

          <Notification :message="error" v-if="error"/>

          <form>
            <v-text-field
              v-model="email"
              label="E-mail"
              required
            ></v-text-field>
            <v-text-field
              v-model="password"
              :append-icon="show ? 'mdi-eye' : 'mdi-eye-off'"
              :rules="[rules.required, rules.min]"
              :type="show ? 'text' : 'password'"
              name="input-10-1"
              label="Hasło"
              hint="Co najmniej 8 znaków"
              counter
              @click:append="show = !show"
            ></v-text-field>
            <v-text-field
              v-model="firstName"
              :counter="10"
              label="Imię"
            ></v-text-field>
            <v-text-field
              v-model="lastName"
              :counter="10"
              label="Nazwisko"
            ></v-text-field>
            <v-btn
              class="mr-4"
              @click="register"
            >
              Zarejestruj!
            </v-btn>
            <v-btn @click="clear">
              Wyczyść
            </v-btn>
          </form>

          <div class="has-text-centered" style="margin-top: 20px">
            Masz już konto? <nuxt-link to="/login">Zaloguj</nuxt-link>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import Notification from '@/components/Notification'

export default {
  components: {
    Notification,
  },
  auth: false
  ,
  data() {
    return {
      firstName: '',
      lastName: '',
      email: '',
      password: '',
      error: null,
      show: false,
      rules: {
        required: value => !!value || 'Required.',
        min: v => v.length >= 8 || 'Min 8 characters',
      },
    }
  },

  methods: {
    clear() {
        this.username = ''
        this.email = ''
        this.password = ''
        this.firstName = ''
        this.lastName = ''
    },
    async register() {
      try {
        await this.$axios.post('auth/register', {
          firstName: this.firstName,
          lastName: this.lastName,
          email: this.email,
          password: this.password
        })

        await this.$auth.loginWith('local', {
          data: {
            email: this.email,
            password: this.password
          },

        })


        this.$router.push('/')
      } catch (e) {
        this.error = e.response.data.message
      }
    }
  }
}
</script>
