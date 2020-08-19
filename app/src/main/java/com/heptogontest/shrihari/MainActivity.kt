package com.heptogontest.shrihari

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.heptogontest.shrihari.databinding.ActivityMainBinding
import com.heptogontest.shrihari.model.data.QuestionModel
import com.heptogontest.shrihari.view.MainActivityListeners
import com.heptogontest.shrihari.view.QuestionListAdapter
import com.heptogontest.shrihari.viewmodel.QuestionsViewModel


class MainActivity : AppCompatActivity()  {

    val TAG = MainActivity::class.java.simpleName
    private val questionsModel: QuestionsViewModel by viewModels()
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout;


    var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //binding view with activity
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this@MainActivity,R.layout.activity_main).apply {
            lifecycleOwner = this@MainActivity
            viewModel = questionsModel;
        }
        (binding as ActivityMainBinding).swipeRefreshLayout.setOnRefreshListener {
            QuestionsViewModel.getQuestionFromService();
        }

        // Call to service method which calls the API to get data
        QuestionsViewModel.getQuestionFromService();


        //Registering Observer for catching data on UI Thread
        val questionObserver = Observer<QuestionModel> { data ->
            // Update the UI, in this case, a TextView.
                Log.d(TAG,"Data Received on UI Thread")
                Log.d(TAG,data.toString())
            Toast.makeText(this@MainActivity,"Content Loaded",Toast.LENGTH_SHORT).show();
            MainActivityListeners.Companion.isLoading = false;
            (binding as ActivityMainBinding).swipeRefreshLayout.isRefreshing = false;


            println("Size == "+  QuestionsViewModel.questionMutableList.value?.questions?.size)

            var adapter:QuestionListAdapter? = null;
            //When First time call is made
            if(adapter==null)
            {
               adapter = data.let { setAdapter(this@MainActivity, it) } as QuestionListAdapter?
            } else // When from second time call is made it need not to create a new instance
            {
                adapter.notifyDataSetChanged();
            }

            var questionListAdapter :QuestionListAdapter = QuestionListAdapter(data,this@MainActivity);
            findViewById<RecyclerView>(R.id.question_list_view).adapter = questionListAdapter;
/*            val questionAdapter = QuestionListAdapter(data)
            (binding as ActivityMainBinding).questionAdapter = questionAdapter;*/
        }

        //Registering the observe for the current life cycle owner
        questionsModel.getStaticInstance().observe(this@MainActivity, questionObserver)


/*        @BindingAdapter(value = ["setAdapter"])
        fun RecyclerView.bindRecyclerViewAdapter(adapter: RecyclerView.Adapter<*>) {
            this.run {
                this.setHasFixedSize(true)
                this.adapter = adapter
            }
        }
        val questionAdapter = QuestionsViewModel.questionMutableList.value?.let { QuestionListAdapter(it) }
        binding!!.questionAdapter = questionAdapter*/



    }

    private fun setAdapter(context: Context,questionsModel:QuestionModel): RecyclerView.Adapter<*>? {
        var adp : QuestionListAdapter = QuestionListAdapter(questionsModel,context);
        binding?.questionListView?.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            addItemDecoration(DividerItemDecoration(context, 0))
             adapter = adp;

        }
        return adp;
    }
}

