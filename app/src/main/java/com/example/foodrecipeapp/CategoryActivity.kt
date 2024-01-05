package com.example.foodrecipeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.foodrecipeapp.databinding.ActivityCategoryBinding
import com.example.foodrecipeapp.databinding.ActivitySearchBinding

class CategoryActivity : AppCompatActivity() {
    private lateinit var rvAdapter:CategoryAdapter
    private lateinit var dataList:ArrayList<Recipe>
    private val binding by lazy {
        ActivityCategoryBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.titleOfCategory.text=intent.getStringExtra("TITTLE")

     title=intent.getStringExtra("TITTLE")
     setUpRecyclerView()

        binding.goHome.setOnClickListener {
            val intent=Intent(this,HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    private fun setUpRecyclerView() {

        dataList= ArrayList()
        binding.rvCategory.layoutManager=
            LinearLayoutManager(this)
        val db= Room.databaseBuilder(this@CategoryActivity,AppDatabase::class.java,"db_name")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .createFromAsset("recipe.db")
            .build()

        val daoObject=db.getDao()
        val recipes=daoObject.getAll()
        for(i in recipes!!.indices)
        {
            if(recipes[i]!!.category.contains(intent.getStringExtra("CATEGORY")!!)) {
                dataList.add(recipes[i]!!)

                rvAdapter=CategoryAdapter(dataList,this)
                binding.rvCategory.adapter=rvAdapter
            }
        }


    }

}