<template>
  <v-form
    ref="form"
    v-model="valid"
    lazy-validation
  >
    <v-overlay :value="loading">
      <v-progress-circular
        indeterminate
        size="64"
      ></v-progress-circular>
    </v-overlay>

    <v-text-field
      v-model="recipe.name"
      :counter="30"
      :rules="nameRules"
      label="Nazwa"
      required
    ></v-text-field>
    <v-text-field
      v-model="recipe.description"
      label="Opis"
    ></v-text-field>
    <v-text-field
      v-model="recipe.imageUrl"
      label="Zdjęcie"
      required
    ></v-text-field>
    <v-checkbox
      v-model="recipe.published"
      label="Opublikowany"
    ></v-checkbox>

    <v-row class="rounded-lg text-no-wrap">
      <v-col cols="12" sm="6" style="border: solid 2px gainsboro;" class="rounded-lg my-5">
        <div class="font-weight-medium text--secondary">Składniki</div>
        <div  v-for="(ingredient, index) in ingredients" >
          <v-list-item>
            <v-list-item-content>
              <v-row>
                <v-col cols="2" align-self="end">
                  <v-icon
                    color="red"
                    @click="removeIngredient(index)"
                  >
                    mdi-minus
                  </v-icon>
                </v-col>
                <v-col cols="8" class="d-flex">
                  <v-text-field
                    v-model="ingredients[index].name"
                    :label="'Składnik '+(index+1)"
                    hide-details="auto"
                    :rules="listItemRules"
                    :disabled="ingredientInputDisabled"
                  >
                  </v-text-field>
                  <v-text-field
                    v-model="ingredients[index].amount"
                    label="Ilość"
                    hide-details="auto"
                    type="number"
                    style="max-width: 100px"
                    :rules="listItemRules"
                    :disabled="ingredientInputDisabled">
                  </v-text-field>
                  <v-select
                    v-model="ingredients[index].unit"
                    :items="units"
                    label="Jednostka"
                    :rules="listItemRules"
                    style="max-width: 100px"
                  ></v-select>
                </v-col>
                <v-col cols="2" align-self="end">
                  <v-icon
                    slot="append"
                    color="green"
                    @click="addIngredient(index)"
                  >
                    mdi-plus
                  </v-icon>
                </v-col>
              </v-row>
            </v-list-item-content>
          </v-list-item>
          <v-divider></v-divider>
        </div>
      </v-col>
      <v-col cols="12" sm="6" style="border: solid 2px gainsboro;" class="rounded-lg my-5">
        <div class="font-weight-medium text--secondary">Kroki przepisu</div>
        <div  v-for="(step, stepIndex) in steps" >
          <v-list-item>
            <v-list-item-content>
              <v-text-field
                v-model="steps[stepIndex]"
                :label="'Krok '+(stepIndex+1)"
                :rules="listItemRules"
                hide-details="auto"
                :disabled="stepInputDisabled"
              >
                <v-icon
                  slot="append"
                  color="green"
                  @click="addStep(stepIndex)"
                >
                  mdi-plus
                </v-icon>
                <v-icon
                  slot="prepend"
                  color="red"
                  @click="removeStep(stepIndex)"
                >
                  mdi-minus
                </v-icon>
              </v-text-field>
            </v-list-item-content>
          </v-list-item>
          <v-divider></v-divider>
        </div>
      </v-col>
    </v-row>


    <v-btn
      :disabled="!valid"
      color="success"
      class="mr-4"
      @click="performAction"
    >
      Zapisz
    </v-btn>
    <v-btn
      color="error"
      class="mr-4"
      @click="reset"
    >
      Wyczyść formularz
    </v-btn>
    <v-btn
      color="primary"
      class="mr-4"
      @click="$router.back()"
    >
      Anuluj
    </v-btn>
  </v-form>
</template>

<script>
export default {
  name: "RecipeForm",
  data(){
    return {
      ingredientInputDisabled: false,
      stepInputDisabled: false,
      valid: false,
      loading: true,
      id: null,
      recipe: {},
      ingredients: [{}],
      steps: [''],
      units: ['szt.', 'kg', 'l', 'g', 'ml', 'łyżeczka', 'łyżka'],
      nameRules: [
        v => !!v || 'Nazwa jest wymagana',
        v => (v && v.length <= 30) || 'Nazwa musi być krótsza niż 30 znaków',
      ],
      listItemRules: [
        v => !!v || 'Pole nie może być puste',
      ],
    }
  },
  props: ['bookId', 'recipeId'],
  mounted() {
    this.valid = false;
    this.id = this.$props.recipeId
    if(this.id){
      this.fetchRecipe(this.id);
    }
    this.recipe.bookId = this.bookId
    this.loading = false;

  },
  methods: {
    cleanArray(array){
      return array.filter(n => n)
    },
    addIngredient(index){
      this.ingredients.splice(index+1, 0, {})
    },
    removeIngredient(index){
      if(this.ingredients.length > 1){
        this.ingredients.splice(index,1)
      }
    },

    addStep(index){
      this.steps.splice(index+1, 0, '')
    },
    removeStep(index){
      if(this.steps.length > 1){
        this.steps.splice(index,1)
      }
    },

    async fetchRecipe(id) {
      const recipe = await this.$axios.$get(`recipe/${id}`)
      this.recipe = recipe
      this.steps = recipe.steps.split('<br>');
      this.ingredients = recipe.ingredients;
    },
    async performAction(){
      //remove empty elements and join using <br>
      this.recipe.steps = this.cleanArray(this.steps).join('<br>')
      this.recipe.ingredients = this.ingredients;

      this.validate();
      if(!this.valid)
        return;

      if(this.id){
        await this.update()
      }
      else{
        await this.add()
      }
    },
    async add(){
      try{
        if(this.valid){
          const newRecipe = await this.$axios.$post('recipe/', this.recipe)
          await this.$router.push({ name: "recipes-id", params: { id: newRecipe.id }})
        }
      }
      catch(err){
        console.log(err)
      }
    },
    async update(){
      try{
        if(this.valid){
          const res = this.$axios.$put('recipe/'+this.id, this.recipe)
          await this.$router.push({ name: "recipes-id" , params: { id: this.id }})
        }
      }
      catch(err){
        console.log(err)
      }
    },
    validate () {
      this.$refs.form.validate()
    },
    reset () {
      this.$refs.form.reset()
    },
    resetValidation () {
      this.$refs.form.resetValidation()
    },
  }
}
</script>

<style scoped>

</style>
