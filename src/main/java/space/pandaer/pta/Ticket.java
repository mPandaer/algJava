package space.pandaer.pta;

import java.util.Scanner;
class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner sc=new Scanner(System.in);
        //System.out.println("请输入票数：");
        int num=sc.nextInt();        //从键盘读入总票数
        Ticket tickets = new Ticket(num);    //产生票

        new Thread(new sellTicketThread(tickets)).start();//sellTicketThread售票
        new Thread(new returnTicketThread(tickets)).start();//returnTicketThread退票

        Thread.sleep(50);    //休眠等待售票和退票执行完毕
        System.out.println(tickets.freeNum);
    }
}

class Ticket {
    int total;        //总票数
    int freeNum;    //多线程共享变量：余票数量
    int soldNum;    //已售出票数
    boolean hasTicket;    //true表示有足够的票出售，false则表示票数不够

    int count = 3;    //线程售票退票次数
    int sellNum = 3;    //单次售票数量
    int returnNum = 2;    //单次退票数量

    public Ticket(int number) {
        total = number;
        freeNum = total;    //售票前：总数与余票数相等
        soldNum = 0;            //已售出票数
        hasTicket = (freeNum >= sellNum);    //余票足够
    }

    synchronized public void sellTicket(int num) throws InterruptedException {
        while(num > freeNum) wait(); //票不够等待
        freeNum -= num;
        soldNum += num;
        System.out.println("售出"+ num +"余票" + freeNum);
        notifyAll();
    }

    synchronized public void returnTicket(int num) throws InterruptedException {

        while (returnNum > soldNum || freeNum >= sellNum) wait(); //没有售票，等待退票
        freeNum += num;
        soldNum -= num;
        System.out.println("退回"+ num +"余票" + freeNum);
        notifyAll();

    }



}

//在下面补充Ticket类的synchronized售票方法


//在下面补充Ticket类的synchronized退票方法，并将Ticket类补充完整


//在下面补充SellTicketThread类，通过实现Runnable实现SellTicketThread类
class sellTicketThread implements Runnable {
    Ticket ticket;
    public sellTicketThread(Ticket ticket) {
        this.ticket = ticket;
    }
    @Override
    public void run() {
        try {
            for (int i =0; i<ticket.count;i++)
                ticket.sellTicket(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

//在下面补充ReturnTicketThread类，通过实现Runnable实现ReturnTicketThread类
class returnTicketThread implements Runnable{
    Ticket ticket;
    public returnTicketThread(Ticket ticket){
        this.ticket = ticket;
    }
    @Override
    public void run() {
        try {
            for (int i =0; i<ticket.count;i++)
                ticket.returnTicket(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

