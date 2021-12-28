<template>
  <v-app>
    <Header/>
    <v-main>
      <v-container>
        <Nuxt />
      </v-container>
    </v-main>
    <Footer/>
  </v-app>
</template>

<script>
import Footer from "@/components/layout/Footer";
import Header from "@/components/layout/Header";
export default {
  components: {Header, Footer},

}
</script>
