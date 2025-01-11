package com.example.pleasesignin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RecipeViewModel : ViewModel() {

    private val _recipesState = MutableStateFlow<List<Recipe>>(emptyList())
    val recipesState: StateFlow<List<Recipe>> = _recipesState

    private val allRecipes = listOf(
        Recipe(1, "Iskender", R.drawable.iskender),
        Recipe(2, "Tavuklu Pilav", R.drawable.iskender),
        Recipe(3, "Baklava", R.drawable.iskender)
    )

    fun setSearchQuery(query: String) {
        viewModelScope.launch {
            val filteredRecipes = if (query.length >= 3) {
                allRecipes.filter {
                    it.title.contains(query, ignoreCase = true)
                }
            } else {
                allRecipes
            }
            _recipesState.value = filteredRecipes
        }
    }
}
