package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),View.OnClickListener {
    lateinit var board:Array<Array<Button>>
    var PLAYER=true
    var TURNCOUNT=0

    var boardstatus=Array(3){IntArray(3)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        board= arrayOf(
            arrayOf(btn1,btn2,btn3),
            arrayOf(btn4,btn5,btn6),
            arrayOf(btn7,btn8,btn9)
        )
        for(i:Array<Button> in board)
        {
            for(button :Button in i)
            {
                button.setOnClickListener(this)
            }
        }
        intializeboardstatus()
        resetbtn.setOnClickListener{
            PLAYER=true
            TURNCOUNT=0;
            intializeboardstatus()

        }

    }

    private fun intializeboardstatus() {
        for(i:Int in 0..2 )
        {
            for(j:Int in 0..2)

            {
                boardstatus[i][j]=-1
                board[i][j].isEnabled=true
                board[i][j].text=""
            }
        }
    }

    override fun onClick(p0: View) {
        when(p0.id)
        {
            R.id.btn1->{
                updatevalue(row=0,col=0,player=PLAYER)
            }
            R.id.btn2-> {
                updatevalue(row=0,col=1,player=PLAYER)

            }
            R.id.btn3-> {
                updatevalue(row=0,col=2,player=PLAYER)

            }
            R.id.btn4-> {
                updatevalue(row=1,col=0,player=PLAYER)

            }
            R.id.btn5-> {
                updatevalue(row=1,col=1,player=PLAYER)

            }
            R.id.btn6-> {
                updatevalue(row=1,col=2,player=PLAYER)

            }
            R.id.btn7-> {
                updatevalue(row=2,col=0,player=PLAYER)

            }
            R.id.btn8-> {
                updatevalue(row=2,col=1,player=PLAYER)

            }
            R.id.btn9-> {
                updatevalue(row=2,col=2,player=PLAYER)

            }
        }
        PLAYER=!PLAYER
        TURNCOUNT++
        if (PLAYER)
        {
            updatedisplay("Players X Turn" )
        }
        else{
            updatedisplay("Players 0 Turn")
        }
        if(TURNCOUNT==9)
        {
            updatedisplay("Game draw")
        }
        checkwinner()
    }

    private fun checkwinner() {
        for( i in 0..2)
        {
            if(boardstatus[i][0]==boardstatus[i][1] && boardstatus[i][0]==boardstatus[i][2])
            {
                if(boardstatus[i][0]==1)
                {
                    updatedisplay("Player X Winner")
                }
                else if(boardstatus[i][0]==0){
                    updatedisplay("Player 0 Winner")
                }
            }
        }
        for(i in 0..2)
        {
            if(boardstatus[0][i]==boardstatus[1][i] && boardstatus[0][i]==boardstatus[2][i])
            {
                if(boardstatus[0][i]==1)
                {
                    updatedisplay("Player X Winner")
                    break
                }
                else if(boardstatus[0][i]==0){
                    updatedisplay("Player 0 Winner")
                    break
                }
            }
        }
        if(boardstatus[0][0]==boardstatus[1][1] && boardstatus[0][0]==boardstatus[2][2])
        {
            if(boardstatus[0][0]==1)
            {
                updatedisplay("Player X Winner")
            }
            else if(boardstatus[0][0]==0){
                updatedisplay("Player 0 Winner")
            }
        }
        if(boardstatus[0][2]==boardstatus[1][1] && boardstatus[0][0]==boardstatus[2][0])
        {
            if(boardstatus[0][2]==1)
            {
                updatedisplay("Player X Winner")
            }
            else if(boardstatus[0][2]==0){
                updatedisplay("Player 0 Winner")
            }
        }

    }

    private fun updatedisplay(s: String) {
        dispalytv.text=s
        if(s.contains("Winner"))
        {
            disablebutton()
        }
    }

    private fun disablebutton() {
        for(i in board)
        {
            for(button in i)
            {
                button.isEnabled=false
            }
        }
    }

    private fun updatevalue(row: Int, col: Int, player: Boolean) {
        val value:Int=if(player) 1 else 0
        val text:String=if(player) "X" else "0"
        board[row][col].apply {
            isEnabled=false
            setText(text)
        }
        boardstatus[row][col]=value

    }
}