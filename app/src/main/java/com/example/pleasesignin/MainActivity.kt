package com.example.pleasesignin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

data class Recipe(
    val id: Int,
    val title: String,
    val imageResId: Int
)

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recipes = listOf(
            Recipe(1, "Iskender", R.drawable.iskender),
            Recipe(2, "Tavuklu Pilav", R.drawable.iskender),
            Recipe(3, "Baklava", R.drawable.iskender)
        )

        val recipesRecyclerView = findViewById<RecyclerView>(R.id.recipe_list)
        recipesRecyclerView.layoutManager = LinearLayoutManager(this)
        recipesRecyclerView.adapter = RecipesAdapter(
            recipes,
            onItemClicked = { recipe ->
                Toast.makeText(this, "Clicked on ${recipe.title} (ID: ${recipe.id})", Toast.LENGTH_SHORT).show()
            },
            onButtonClicked = { recipe, action ->
                Toast.makeText(this, "$action clicked for ${recipe.title} (ID: ${recipe.id})", Toast.LENGTH_SHORT).show()
            }
        )
    }
}


class RecipesAdapter(
    private val recipes: List<Recipe>,
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
}
