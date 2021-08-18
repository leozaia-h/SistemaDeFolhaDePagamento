import java.util.Calendar;

public class FolhaDePagamento {
    
    public int hoje() {
        Calendar c = Calendar.getInstance();
        int dia = c.get(Calendar.DAY_OF_MONTH);
        
        return dia;
    }

    public String sHoje() {
        Calendar c = Calendar.getInstance();
        int dia = c.get(Calendar.DAY_OF_WEEK);
        
        if(dia == 1) {
            return "domingo";
        }
        else if(dia == 2) {
            return "segunda";
        }
        else if(dia == 3) {
            return "terça";
        }
        else if(dia == 4) {
            return "quarta";
        }
        else if(dia == 5) {
            return "quinta";
        }
        else if(dia == 6) {
            return "sexta";
        }
        else if(dia == 7) {
            return "sabado";
        }

        return "erro ao saber o dia";
    }

    public int diasMes() {
        Calendar c = Calendar.getInstance();
        int diasMes = c.getActualMaximum(Calendar.DAY_OF_MONTH);

        return diasMes;
    }

    public int lastMonthDay() {
        Calendar c = Calendar.getInstance();
        int aux = c.getActualMaximum(Calendar.DAY_OF_MONTH);

        System.out.printf("%d\n", aux);
        if(c.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
            aux -= 1;
        }
        
        return aux;
    }

    public int getUltimoDiaUtilDoMes() {
        Calendar calendar = Calendar.getInstance();
        //muda a data da variável para o último dia do mês
        calendar.add(Calendar.MONTH, 1);  
        calendar.set(Calendar.DAY_OF_MONTH, 1);  
        calendar.add(Calendar.DATE, -1);
        //enquanto for sábado, domingo ou feriado
        while(calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY ||
                calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || 
                ehFeriado(calendar)) {
            //decrementa a data em um dia
            calendar.add(Calendar.DATE, -1);            
        }
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public boolean ehFeriado(Calendar calendar) {
        Calendar feriado = Calendar.getInstance();
        //considerando 30 de abril como feriado
        feriado.set(calendar.get(Calendar.YEAR), Calendar.APRIL, 30);
        if(calendar.get(Calendar.DAY_OF_YEAR) == feriado.get(Calendar.DAY_OF_YEAR)) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean verSemanal()  {
        Calendar calendar = Calendar.getInstance();
        if(calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
            return true;
        }
        return false;
    }
}
