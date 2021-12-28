<template>
  <v-expansion-panels focusable class="mt-3">
    <v-expansion-panel
      v-for="recipe in recipes"
      :key="recipe.id"
    >
      <v-expansion-panel-header v-slot="{ open }">
        <v-row no-gutters>
          <v-col cols="4">
            {{recipe.name}}
          </v-col>
          <v-col
            cols="8"
            class="text--secondary"
          >
            <v-fade-transition leave-absolute>
              <v-row
                no-gutters
                style="width: 100%"
              >
                <v-col cols="6">
                </v-col>
                <v-col cols="6">
                  Ostatnio zaktualizowany: {{ new Date(recipe.modifiedAt).toLocaleString() || 'Nigdy' }}
                </v-col>
              </v-row>
            </v-fade-transition>
          </v-col>
        </v-row>
      </v-expansion-panel-header>
      <v-expansion-panel-content>
        <v-row no-gutters>
          <v-col cols="5">
            <v-card-text>
              {{recipe.description}}
            </v-card-text>
          </v-col>

          <v-divider
            vertical
            class="mx-4"
          ></v-divider>

          <v-col cols="3">
            <v-card-text>
              Publiczny: <b>{{recipe.published ? "Tak" : "Nie"}}</b>
            </v-card-text>
          </v-col>
        </v-row>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            text
            color="success"
            @click="$router.push({name: 'recipes-id', params: { id: recipe.id }})"
          >
            Zobacz
          </v-btn>
          <v-btn
            text
            color="primary"
            @click="$router.push({name: 'recipes-update-id', params:{ id: recipe.id}})"
          >
            Aktualizuj
          </v-btn>
          <Dialog btnLabel="Usuń"
                  title="Czy chcesz usunąć ten przepis?"
                  description="Spowoduje to usunięcie wszystkich informacji związanych z wybranym przepisem."
                  color="error"
                  :actionUrl="'recipe/'+recipe.id"
                  actionType="delete"
                  style="max-width: 90px"
                  class="mb-0"
                  route="_reload"
          />
        </v-card-actions>
      </v-expansion-panel-content>
    </v-expansion-panel>
  </v-expansion-panels>
</template>

<script>
export default {
  name: "RecipeList",
  data(){
    return{
    }
  },
  props: ['recipes'],
  mounted() {

  }
}
</script>

<style scoped>

</style>
