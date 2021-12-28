<template>
  <div>
    <v-navigation-drawer
      v-model="drawer"
      :mini-variant="miniVariant"
      :clipped="clipped"
      fixed
      app
      color="orange"
    >
      <v-list>
        <v-list-item
          v-for="(item, i) in items"
          :key="i"
          :to="item.to"
          router
          exact
          v-if="isAuthenticated || item.noAuth"
        >
          <v-list-item-action>
            <v-icon>{{ item.icon }}</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title v-text="item.title"/>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>
    <v-app-bar
      :clipped-left="clipped"
      fixed
      app
    >
      <v-app-bar-nav-icon @click.stop="drawer = !drawer"/>
      <v-btn
        icon
        @click.stop="miniVariant = !miniVariant"
      >
        <v-icon>mdi-{{ `chevron-${miniVariant ? 'right' : 'left'}` }}</v-icon>
      </v-btn>
      <v-toolbar-title style="cursor: pointer" v-text="title" @click="$router.push({name: 'index'})" />
      <v-spacer/>

      <div v-if="isAuthenticated">
        <a >

        </a>
        <div >
          <v-btn text to="/profile" color="orange darken-3">{{ loggedInUser.email }}</v-btn>
          <v-btn text @click="logout" color="orange darken-3">Wyloguj</v-btn>
        </div>

      </div>
      <template v-else>
        <v-btn text to="/register" color="orange darken-3">Rejestracja</v-btn>
        <v-btn text to="/login" color="orange darken-3">Zaloguj</v-btn>
      </template>

    </v-app-bar>
  </div>
</template>

<script>
import {mapGetters} from "vuex";

export default {
  name: "Header",
  data() {
    return {
      items: [
        {
          icon: 'mdi-apps',
          title: 'Sweet Spot Recipes',
          to: '/',
          noAuth: true
        },
        {
          icon: 'mdi-pasta',
          title: 'Przepiśniki',
          to: '/books',
          noAuth: false
        }
      ],
      drawer: false,
      miniVariant: false,
      clipped: false,
      fixed: false,
      right: true,
      rightDrawer: false,
      title: 'Sweet Spot - Przepisy'
    }
  },
  computed: {
    ...mapGetters(['isAuthenticated', 'loggedInUser'])
  },
  methods: {
    async logout() {
      await this.$auth.logout();
    },
  },
}
</script>

<style scoped>

</style>
