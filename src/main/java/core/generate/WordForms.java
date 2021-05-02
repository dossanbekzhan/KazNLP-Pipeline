package core.generate;

import java.util.ArrayList;
import java.util.List;

public class WordForms {
    boolean b = true;
    Charater charater = new Charater();
    final static private String[][] regularPlural = {
            {"дар", "дер", "лмнңжз"},
            {"тар", "тер", "пкқтсфхцчшбвгд"},
            {"лар", "лер", "аоұыяəөүіерйу"},
    };

    String[][] persMatr1 = {
            {"мын", "мін", "аоұыяəөүіерлмнңйу", "Ж-1Ж"},
            {"бын", "бін", "жз", "Ж-1Ж"},
            {"пын", "пін", "пкқтсфхцчшбвгд", "Ж-1Ж"},
            {"мыз", "міз", "аоұыяəөүіерлйу", "Ж-1К"},
            {"быз", "біз", "жзмнң", "Ж-1К"},
            {"пыз", "піз", "пкқтсфхцчшбвгд", "Ж-1К"},
            {"сың", "сің", "аоұыяəөүіерлжзмнңйупкқтсфхцчшбвгд", "Ж-2Ж"},
            {"сыз", "сіз", "аоұыяəөүіерлжзмнңйупкқтсфхцчшбвгд", "Ж-2Ж"},
    };

    List<Info> addPers(Info i) {
        List<Info> list = new ArrayList<Info>();
        charater.setWord(i.forma);
        for (String[] s : persMatr1) {
            if (s[2].contains(charater.endLetter + "")) {
                if (charater.isZhuan)
                    list.add(cretInfo(i, s[0], s[3]));
                else list.add(cretInfo(i, s[1], s[3]));
            }
        }
        return list;
    }

    String assimilation(String s) {

        if (s.charAt(s.length() - 1) == 'қ' && b)
            return s.substring(0, s.length() - 1) + "ғ";
        if (s.charAt(s.length() - 1) == 'п')
            return s.substring(0, s.length() - 1) + "б";
        if (s.charAt(s.length() - 1) == 'к')
            return s.substring(0, s.length() - 1) + "г";
        return s;
    }

    Info addPlural(Info i) {
        charater.setWord(i.forma);
        for (String[] s : regularPlural) {
            if (s[2].contains(charater.endLetter + "")) {
                if (charater.isZhuan) {
                    return cretInfo(i, s[0], "КЖ");
                } else {
                    return cretInfo(i, s[1], "КЖ");

                }
            }
        }
        return i;
    }

    List<Info> addCase(Info i, boolean bbb) {
        List<Info> list = new ArrayList<Info>();
        charater.setWord(i.forma);


        String[][] caseMatrix;
        if (bbb)
            caseMatrix = bseCase;
        else
            caseMatrix = depCase;


        for (String[] str : caseMatrix) {
            if (str[2].contains(charater.endLetter + "")) {
                if (!charater.endDauysty || !charater.endIsDauysty(str[0].charAt(0))) {
                    if (str[1].length() > 0) {
                        if (charater.isZhuan) {
                            list.add(cretInfo(i, str[0], str[3]));
                        } else {
                            list.add(cretInfo(i, str[1], str[3]));
                        }
                    } else {
                        list.add(cretInfo(i, str[0], str[3]));
                    }
                }
            }
        }
        return list;
    }

    List<Info> addDep(Info i) {
        List<Info> list = new ArrayList<Info>();
        final Info tmp = new Info(i.getForma(), i.getTerminations());
        charater.setWord(tmp.forma);
        tmp.forma = assimilation(tmp.forma);
        if (charater.endDauysty) {
            list.add(cretInfo(tmp, "м", "ТЖ-1Ж"));
            if (charater.isZhuan) {
                list.add(cretInfo(tmp, "мыз", "ТЖ-1К"));
            } else {
                list.add(cretInfo(tmp, "міз", "ТЖ-1К"));
            }

            list.add(cretInfo(tmp, "ң", "ТЖ-2Ж"));
            if (charater.isZhuan) {
                list.add(cretInfo(tmp, "ңыз", "ТЖ-2К"));
            } else {
                list.add(cretInfo(tmp, "ңіз", "ТЖ-2К"));
            }
            if (charater.isZhuan) {
                list.add(cretInfo(tmp, "сы", "ТЖ-3Ж"));
            } else {
                list.add(cretInfo(tmp, "сі", "ТЖ-3Ж"));
            }
        } else {
            if (charater.isZhuan) {
                list.add(cretInfo(tmp, "ым", "ТЖ-1Ж"));
                list.add(cretInfo(tmp, "ымыз", "ТЖ-1К"));
                list.add(cretInfo(tmp, "ың", "ТЖ-2Ж"));
                list.add(cretInfo(tmp, "ыңыз", "ТЖ-2К"));
                list.add(cretInfo(tmp, "ы", "ТЖ-3Ж"));
            } else {
                list.add(cretInfo(tmp, "ім", "ТЖ-1Ж"));
                list.add(cretInfo(tmp, "іміз", "ТЖ-1К"));
                list.add(cretInfo(tmp, "ің", "ТЖ-2Ж"));
                list.add(cretInfo(tmp, "іңіз", "ТЖ-2Ж"));
                list.add(cretInfo(tmp, "і", "ТЖ-3Ж"));
            }
        }
        return list;
    }

    Info cretInfo(Info i, String term, String type) {
        Info info = new Info();
        if (charater.endIsDauysty(i.forma.charAt(i.forma.length() - 1)) && charater.endIsDauysty(term.charAt(0))) {
            info.forma = i.forma + term.substring(1, term.length());
        } else info.forma = i.forma + term;

        info.terminations.addAll(i.terminations);
        info.terminations.add(new Termination(term, type));
        return info;
    }

    String[][] bseCase = {
            {"бен", "", "жз", "КС"},
            {"пен", "", "пкқтсфхцчшбвгд", "КС"},
            {"дан", "ден", "лруйжзаоұыяəөүіе", "ШС"},
            {"тан", "тен", "бвгдпкқтсфхцчш", "ШС"},
            {"тың", "тің", "бвгдпкқтсфхцчш", "ІС"},
            {"ның", "нің", "мнңаоұыяəөүіе", "ІС"},
            {"дың", "дің", "июлруйжз", "ІС"},
            {"нан", "нен", "імнң", "ШС"},
            {"мен", "", "аоұыяəөүіерлмнңйу", "КС"},
            {"да", "де", "жзаоұыяəөүіерлмнңйу", "ЖС"},
            {"та", "те", "пкқтсфхцчшбвгд", "ЖС"},
            {"ды", "ді", "июжзрлмнңйу", "ТС"},
            {"ты", "ті", "пкқтсфхцчшбвгд", "ТС"},
            {"ны", "ні", "аоұыяəөүіе", "ТС"},
            {"ын", "ін", "жзаоұыяəөүіерлмнңйу", "ТС"},
            {"ға", "ге", "жзаоұыяəөүіерлмнңйу", "БС"},
            {"на", "не", "ыі", "БС"},
            {"қа", "ке", "бвгдпкқтсфхцчш", "БС"},
    };
    String[][] depCase = {
            // {"бен","","жз","КС"},
            // {"пен","","пкқтсфхцчшбвгд","КС"},
            // {"дан","ден","лруйжзаоұыяəөүіе","ШС"},
            // {"тан","тен","бвгдпкқтсфхцчш","ШС"},
            //{"тың","тің","бвгдпкқтсфхцчш","ІС"},
            // {"дың","дің","июлруйжз","ІС"},
            // {"та","те","пкқтсфхцчшбвгд","ЖС"},
            //{"ты","ті","пкқтсфхцчшбвгд","ТС"},
            //{"ын","ін","жзаоұыяəөүіерлмнңйу","ТС"},
            {"ның", "нің", "мнңаоұыяəөүіе", "ІС"},
            {"нан", "нен", "іымнң", "ШС"},
            {"мен", "", "аоұыяəөүіерлмнңйу", "КС"},
            {"да", "де", "жзаоұяəөүерлмнңйу", "ЖС"},
            {"нда", "нде", "ыі", "ЖС"},
            {"ды", "ді", "июжзрлмнңйу", "ТС"},
            {"н", "н", "ыі", "ТС"},
            {"на", "не", "ыі", "БС"},
            {"а", "е", "мң", "БС"},
            {"ға", "ге", "з", "БС"},
    };

    List<Info> kosemshe(Info i, boolean b) {
        List<Info> list = new ArrayList<Info>();
        charater.setWord(i.forma);
        if (charater.endDauysty) {
            list.add(cretInfo(i, "п", "Көсемше"));
            list.add(cretInfo(i, "й", "Көсемше"));
        } else {
            Info ii = new Info();
            String ss = assimilation(i.forma);
            ii.forma = ss;
            if (charater.isZhuan) {
                list.add(cretInfo(ii, "ып", "Көсемше"));
                Info i1 = new Info();

                list.add(cretInfo(ii, "а", "Көсемше"));
            } else {
                list.add(cretInfo(ii, "іп", "Көсемше"));
                list.add(cretInfo(ii, "е", "Көсемше"));
            }
        }
        if (b) {
            if ("пкқтсш".contains(charater.endLetter + "")) {
                if (charater.isZhuan)
                    list.add(cretInfo(i, "қалы", "Көсемше"));
                else
                    list.add(cretInfo(i, "келі", "Көсемше"));
            } else {
                if (charater.isZhuan)
                    list.add(cretInfo(i, "ғалы", "Көсемше"));
                else
                    list.add(cretInfo(i, "гелі", "Көсемше"));
            }
        }
        return list;
    }

    List<Info> esimshe(Info i) {
        List<Info> list = new ArrayList<Info>();
        charater.setWord(i.forma);
        Info i1 = new Info();
        String str = assimilation(i.forma);
        i1.forma = str;

        if ("пкқтсш".contains(charater.endLetter + "")) {
            if (charater.isZhuan) {
                list.add(cretInfo(i, "қан", "Есімше"));
                final Info tmp = cretInfo(i, "пақ", "Есімше");
                list.add(tmp);
            } else {
                list.add(cretInfo(i, "кен", "Есімше"));
                list.add(cretInfo(i, "пек", "Есімше"));
            }
        } else {
            if (charater.isZhuan) {
                list.add(cretInfo(i, "ған", "Есімше"));

                if ("жз".contains(charater.endLetter + ""))
                    list.add(cretInfo(i, "бақ", "Есімше"));
                else {
                    list.add(cretInfo(i, "мақ", "Есімше"));
                }
            } else {
                list.add(cretInfo(i, "ген", "Есімше"));
                if ("жз".contains(charater.endLetter + ""))
                    list.add(cretInfo(i, "бек", "Есімше"));
                else
                    list.add(cretInfo(i, "мек" + "", "Есімше"));
            }
        }
        if (charater.endDauysty) {
            list.add(cretInfo(i, "р", "Есімше"));
            if(charater.endLetter=='ы'){
                Info k=new Info();
                k.forma=i.forma.substring(0,i.forma.length()-1);
                k.setTerminations(i.getTerminations());
            list.add(cretInfo(k,"итын","Есімше"));
            }
            else if (charater.isZhuan) {
                list.add(cretInfo(i, "йтын", "Есімше"));
            } else
                list.add(cretInfo(i, "йтін", "Есімше"));
        } else {
            if (charater.isZhuan) {
                list.add(cretInfo(i1, "атын", "Есімше"));
                list.add(cretInfo(i1, "ар", "Есімше"));
            } else {
                list.add(cretInfo(i1, "етін", "Көсемше"));
                list.add(cretInfo(i1, "ер", "Көсемше"));
            }
        }


        return list;
    }

    List<Info> rai(Info info) {
        List<Info> list = new ArrayList<Info>();
        charater.setWord(info.forma);
        if (charater.isZhuan) {
            list.add(cretInfo(info, "са", "ШарттыРай"));
            list.add(cretInfo(list.get(0), "м", "ЖЖ-1Ж"));
            list.add(cretInfo(list.get(0), "ң", "ЖЖ-2Ж"));
            list.add(cretInfo(list.get(0), "қ", "ЖЖ-1К"));
            Info ii = new Info();
            String ss = assimilation(info.forma);
            ii.forma = ss;
            list.add(cretInfo(ii, "айын", "БұйрықРай-1Ж-ЖТ"));
            list.add(cretInfo(ii, "айық", "БұйрықРай-1Ж-КТ"));
            list.add(cretInfo(ii, "ыңдар", "БұйрықРай-2Ж-КТ"));
            list.add(cretInfo(ii, "ыңыз", "БұйрықРай-2Ж-ЖТ"));
            list.add(cretInfo(ii, "ыңыздар", "БұйрықРай-2Ж-КТ"));
            list.add(cretInfo(info, "сын", "БұйрықРай-3Ж-ЖТ"));
        } else {
            list.add(cretInfo(info, "се", "ШарттыРай"));
            list.add(cretInfo(list.get(0), "м", "ЖЖ-1Ж"));
            list.add(cretInfo(list.get(0), "ң", "ЖЖ-2Ж"));
            list.add(cretInfo(list.get(0), "к", "ЖЖ-1К"));
            list.add(cretInfo(info, "ейін", "БұйрықРай-1Ж-ЖТ"));
            list.add(cretInfo(info, "ейік", "БұйрықРай-1Ж-КТ"));
            list.add(cretInfo(info, "іңдер", "БұйрықРай-2Ж-КТ"));
            list.add(cretInfo(info, "іңіз", "БұйрықРай-2Ж-ЖТ"));
            list.add(cretInfo(info, "іңіздер", "БұйрықРай-2Ж-КТ"));
            list.add(cretInfo(info, "сін", "БұйрықРай-3Ж-ЖТ"));
        }
        return list;

    }

    List<Info> shyrai(Info info) {
        List<Info> list = new ArrayList<Info>();
        charater.setWord(info.forma);
        if (charater.endDauysty) {
            if (charater.isZhuan)
                list.add(cretInfo(info, "рақ", "СалысШырай"));
            else
                list.add(cretInfo(info, "рек", "СалысШырай"));

        } else {
            if (charater.isZhuan)
                list.add(cretInfo(info, "ырақ", "СалысШырай"));
            else
                list.add(cretInfo(info, "ірек", "СалысШырай"));
        }
        if ("йру".contains(charater.endLetter + "") || charater.endDauysty) {
            if (charater.isZhuan)
                list.add(cretInfo(info, "лау", "СалысШырай"));
            else
                list.add(cretInfo(info, "леу", "СалысШырай"));
        } else if ("қкстпш".contains(charater.endLetter + "")) {
            if (charater.isZhuan)
                list.add(cretInfo(info, "тау", "СалысШырай"));
            else
                list.add(cretInfo(info, "теу", "СалысШырай"));
        } else {
            if (charater.isZhuan)
                list.add(cretInfo(info, "дау", "СалысШырай"));
            else
                list.add(cretInfo(info, "деу", "СалысШырай"));
        }

        return list;
    }
}
