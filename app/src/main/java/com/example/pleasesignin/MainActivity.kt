package com.example.pleasesignin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

data class Recipe(
    val id: Int,
    val title: String,
    val imageResId: Int
)

class MainActivity : AppCompatActivity() {

    private val recipeViewModel: RecipeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recipesRecyclerView = findViewById<RecyclerView>(R.id.recipe_list)
        val searchView = findViewById<androidx.appcompat.widget.SearchView>(R.id.search_view)

        recipesRecyclerView.layoutManager = LinearLayoutManager(this)

        val recipesAdapter = RecipesAdapter(
            emptyList(),
            onItemClicked = { recipe ->
            },
            onButtonClicked = { recipe, action ->
            }
        )
        recipesRecyclerView.adapter = recipesAdapter

        lifecycleScope.launch {
            recipeViewModel.recipesState.collect { recipes ->
                recipesAdapter.updateRecipes(recipes)
            }
        }

        searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                recipeViewModel.setSearchQuery(newText.orEmpty())
                return true
            }
        })
    }
}


class RecipesAdapter(
    private var recipes: List<Recipe>,
    private val onItemClicked: (Recipe) -> Unit,
    private val onButtonClicked: (Recipe, String) -> Unit
) : RecyclerView.Adapter<RecipesAdapter.RecipeViewHolder>() {

    inner class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.recipe_title)
        private val image: ImageView = itemView.findViewById(R.id.recipe_image)
        private val likeButton: Button = itemView.findViewById(R.id.like_button)

        fun bind(recipe: Recipe) {
            title.text = recipe.title
            image.setImageResource(recipe.imageResId)
            itemView.setOnClickListener { onItemClicked(recipe) }
            likeButton.setOnClickListener { onButtonClicked(recipe, "like") }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recipe, parent, false)
        return RecipeViewHolder(view)
    }

    override fun getItemCount(): Int = recipes.size

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(recipes[position])
    }

    fun updateRecipes(newRecipes: List<Recipe>) {
        recipes = newRecipes
        notifyDataSetChanged()
    }
}
