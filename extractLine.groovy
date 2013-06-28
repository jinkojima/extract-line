//検索用キーワード。これにヒットしたら出力「しない」
def keywordList = /(java\.|javax\.|jp\.co\.hogehoge)/

//ファイルから対象行の抽出
extractLine(parseArgs(args), keywordList)

//引数の数に応じてで出力先をデフォかどうか決定する
def parseArgs(String...args) {
    switch (args.size()) {
        case 1:
            return [targetFile:args[0], outputFile:"defaultOutputFile.txt"]
        case 2:
            return [targetFile:args[0], outputFile:args[1]]
        default:
            System.err.println "usage: groovy extractLine.groovy TARGETFILE OUTPUTFILE"
            System.exit 1
    }
}
//対象行の抽出
//ファイルの各行に対して、キーワードと評価
def extractLine(argsMap, keywordList) {
    //対象ファイルと出力先ファイル
    targetFile = new File(argsMap.targetFile)
    outputFile = new File(argsMap.outputFile)

    targetFile.eachLine { line->
        if (line =~ keywordList) {
            //println "KEYWORD HIT"
        } else {
            outputFile.append(line + "\n")
        }
    }
}
