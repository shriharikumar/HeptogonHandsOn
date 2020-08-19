package com.heptogontest.shrihari.view

import android.app.Activity
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.heptogontest.shrihari.R
import com.heptogontest.shrihari.model.data.Question
import com.heptogontest.shrihari.model.data.QuestionModel
import com.heptogontest.shrihari.viewmodel.QuestionTypes
import kotlinx.android.synthetic.main.question_item.view.*
import java.lang.Exception


class QuestionListAdapter(
    private val items: QuestionModel,
    var context: Context
): RecyclerView.Adapter<QuestionListAdapter.ViewHolder>() {


    //Intialized the neccesary local scope variable for state management
    var questions: ArrayList<Question>
    var textStatus:Boolean = false;
    var emailStatus:Boolean = false;
    var textTypeText:String = "";
    var emailTypeText:String = "";
    var spinnerStatus:Boolean = false;
    var radioButtonStatus:Boolean = false;
    init {
         questions = items.questions!!; // question set list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.question_item, parent, false))
    }


    override fun getItemCount(): Int {
        return questions?.size!!
    }


    //Bind the item with the view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(questions?.get(position))

        try {
            val currentQuestion = questions?.get(position);

            Log.d("Type", questions?.get(position).type)
            holder.itemView.type_text_question.text = questions.get(position).question.toString()

            //Visibility Modifier for hiding the QA type
            if (currentQuestion.type?.equals(QuestionTypes.text.name)!!) {
                holder.typeText?.visibility = View.VISIBLE
                holder.typeEmail?.visibility = View.GONE
                holder.typeSingleChoiceRdGrp?.visibility = View.GONE
                holder.typeDropDown?.visibility = View.GONE
                holder.submitButton?.visibility = View.GONE
                holder.submitButton?.visibility = View.GONE

                holder.typeText?.addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(s: Editable) { textTypeText = s.toString(); }
                    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
                    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int
                    ) {
                    }
                })
                holder.typeEmail?.setFocusable(false);
            } else if (currentQuestion.type?.equals(QuestionTypes.emailtext.name)!!) {
                holder.typeText?.visibility = View.GONE
                holder.typeEmail?.visibility = View.VISIBLE
                holder.typeSingleChoiceRdGrp?.visibility = View.GONE
                holder.typeDropDown?.visibility = View.GONE
                holder.submitButton?.visibility = View.GONE

                holder.typeEmail?.addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(s: Editable) { emailTypeText = s.toString(); }
                    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
                    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
                })
                holder.typeSingleChoiceRdGrp?.setFocusable(false)
            } else if (currentQuestion.type?.equals(QuestionTypes.radio.name)!!) {
                holder.typeText?.visibility = View.GONE
                holder.typeEmail?.visibility = View.GONE
                holder.typeSingleChoiceRdGrp?.visibility = View.VISIBLE
                holder.typeDropDown?.visibility = View.GONE
                holder.submitButton?.visibility = View.GONE

                for (i in 1..currentQuestion.values?.size!!) {
                    val rdbtn = RadioButton(context)
                    rdbtn.id = View.generateViewId()
                    rdbtn.text = currentQuestion.values?.get(i - 1)?.value.toString()
                    holder.typeSingleChoiceRdGrp?.addView(rdbtn)
                }
                holder.typeSingleChoiceRdBtn1?.text = currentQuestion.values.get(0).value
                holder.typeSingleChoiceRdGrp?.setOnCheckedChangeListener(object :
                    RadioGroup.OnCheckedChangeListener {
                    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
                        val id = group?.getCheckedRadioButtonId();
                        var rb: RadioButton = (context as Activity).findViewById(id!!);
                        val radioText: String = rb.getText().toString();
                        currentQuestion.answers = radioText;
                        radioButtonStatus = true;
                    }
                });

            } else if (currentQuestion.type?.equals(QuestionTypes.dropdown.name)!!) {
                holder.typeText?.visibility = View.GONE
                holder.typeEmail?.visibility = View.GONE
                holder.typeSingleChoiceRdGrp?.visibility = View.GONE
                holder.typeDropDown?.visibility = View.VISIBLE
                holder.submitButton?.visibility = View.GONE


                var arrTmp = arrayListOf<String>()
                for (it in currentQuestion.values!!) {
                    arrTmp.add(it.value!!)
                }
                var aa = ArrayAdapter(context, android.R.layout.simple_spinner_item, arrTmp)
                aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

                with(holder.typeDropDown)
                {
                    this!!.adapter = aa
                    setSelection(0, false)
                    prompt = currentQuestion.question
                    gravity = Gravity.CENTER

                }
                holder.typeDropDown?.onItemSelectedListener = object :
                    AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>,
                        view: View, position: Int, id: Long
                    ) {
                        Toast.makeText(
                            context,
                            arrTmp[position], Toast.LENGTH_SHORT
                        ).show()
                        spinnerStatus = true;
                    }

                    override fun onNothingSelected(parent: AdapterView<*>) {
                        // write code to perform some action
                    }
                }

            } else {
                holder.textViewQuestion?.visibility = View.GONE;
                holder.typeText?.visibility = View.GONE
                holder.typeEmail?.visibility = View.GONE
                holder.typeSingleChoiceRdGrp?.visibility = View.GONE
                holder.typeDropDown?.visibility = View.GONE
                holder.submitButton?.visibility = View.GONE


            }
            if (position == itemCount - 1) {
                holder.submitButton?.visibility = View.VISIBLE
                holder.errorText?.visibility = View.GONE
            } else {
                holder.errorText?.visibility = View.GONE
            }

            //Validation for 4 types of answers : TEXT,EMAIL,RADIO AND SPINNER
            holder.submitButton?.setOnClickListener {
                Log.d("Validate Email", "check")
                if (UiUtils.isTextValid(textTypeText)) {
                    Log.d("Validate text", "Fail!" + currentQuestion?.errorMessage.toString())
                    textStatus = false;
                } else {
                    Log.d("Validate text", "Pass!")
                    textStatus = true;
                }
                if (!UiUtils.isValidEmail(emailTypeText)) {
                    Log.d("Validate email", "Fail!" + currentQuestion?.errorMessage.toString())
                    emailStatus = false;

                } else {
                    Log.d("Validate email", "Pass!")

                    emailStatus = true;
                }

                holder.errorText?.visibility = View.VISIBLE
                holder.errorText?.setText(
                    "Text Validate $textStatus,\n" +
                            "Email Validate $emailStatus\n, Radio Validate $radioButtonStatus\n,Spinner Validate $spinnerStatus\n"
                )

                Toast.makeText(
                    context,
                    "Text Validate $textStatus,Email Validate $emailStatus, Radio Validate $radioButtonStatus,Spinner Validate $spinnerStatus",
                    Toast.LENGTH_SHORT
                ).show();

            }
        } catch (e:Exception)
        {
            Log.e(QuestionListAdapter::class.java.simpleName,e.localizedMessage.toString())
        }

    }


    //Class model for data item - single item
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewQuestion: TextView? = null
        var typeText: EditText? = null
        var typeSingleChoiceRdGrp: RadioGroup? = null
        var typeSingleChoiceRdBtn1: RadioButton? = null
        var typeDropDown: Spinner? = null
        var typeEmail: EditText? = null
        var submitButton: Button? = null
        var errorText: TextView? = null


        fun bind(item: Question?) {
            with(itemView) {
                textViewQuestion = findViewById(R.id.type_text_question)
                typeText = findViewById(R.id.type_text_answer_editText)
                typeEmail = findViewById(R.id.type_emailText_answer_editText)
                typeSingleChoiceRdGrp = findViewById(R.id.type_singleChoice_answer_RdBtnGrp)
                typeDropDown = findViewById(R.id.type_dropDown_answer_spinner)
                submitButton = findViewById(R.id.submit_button)
                errorText = findViewById(R.id.error_text)

            }
        }

    }
}