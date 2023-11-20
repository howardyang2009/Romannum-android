package com.example.romannumber_andriod

import kotlin.math.roundToInt

class RomannumCalculator {
    var arrayNum = arrayOf(1, 5, 10, 50, 100, 500, 1000)
    var arrayLetter = arrayOf('I', 'V', 'X', 'L', 'C', 'D', 'M')
    var numMin:Int = -1
    var numMax:Int = -1

    fun init() {
        numMin = 1
        numMax = arrayNum[arrayNum.size-1]*4-1 //3999
    }

    fun num2str(number:Int) : String {
        var str:String = ""
        var times: Int
        var num:Int = number

        if (num<numMin || num>numMax)
            throw Exception("number exceed scope [$numMin, $numMax]")

        var i:Int = arrayNum.size - 1
        while (i >= 0) {
            times = num/arrayNum[i]
            if (times == 4) {
                // for IV CL CD 4 40 400
                str += arrayLetter[i].toString() + arrayLetter[i+1].toString()
            } else {
                var j:Int = 0
                while (j<times) {
                    str += arrayLetter[i].toString()
                    j++
                }
            }

            num-= times * arrayNum[i]

            //for IX VL CM 9 90
            if (!_isOdd(num=i) && i!=0) {
                val temp1:Int = (arrayNum[i] * 0.9).roundToInt()
                if (num >= temp1) {
                    str += arrayLetter[i-2].toString() + arrayLetter[i].toString()
                    num -= temp1
                }
            }
            i--
        }
        return str
    }

    fun str2num(inputStr:String) : Int {
        var num:Int
        var i:Int
        var currentCharIndex:Int
        var nextCharIndex:Int

        var currentChar:Char
        var nextChar:Char
        var str:String
        var temp:String

        str = inputStr.trim()
        if (str.length <= 0)
            throw Exception("empty is invalid")

        num = 0
        i = 0
        nextCharIndex = -1
        while (i<str.length) {
            currentChar = str[i]
            currentCharIndex = _charIndex(currentChar)
            if (i < str.length-1) {
                nextChar = str[i+1]
                nextCharIndex = _charIndex(nextChar)
            }

            if (currentCharIndex < nextCharIndex)
                num -= _char2num(currentChar)
            else
                num += _char2num(currentChar)

            i++
            nextCharIndex = -1
        }

        temp = num2str(num)
        if (str != temp)
            throw Exception("non-standard Roman Number")

        return num
    }

    fun _isOdd(num:Int) : Boolean {
        return num % 2 == 0
    }

    fun _charIndex(char:Char):Int {
        val index = arrayLetter.indexOf(char)
        if (index != -1)
            return index

        val error = "valid letter just could be " + arrayLetter.joinToString()
        throw Exception(error)
    }

    fun _char2num(char: Char):Int {
        val index = _charIndex(char)
        return arrayNum[index]
    }
}