 import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.URI;

public class PortfolioApp extends JPanel implements ActionListener, MouseListener, MouseMotionListener {
    private Timer timer;
    private int lastMouseX = 0, lastMouseY = 0;
    private int currentSection = 0;
    private int scrollOffset = 0;

    private String[] sections = {"HOME", "SKILLS", "PROJECTS", "CONTACT"};

    private String[] portfolioContent = {
        "👋 Hi, I'm Purnika Naga Durga Jyothi\n" +
        "💡 Building Intelligent Solutions With Code\n" +
        "🤖 AI & Coding Enthusiast, turning ideas into intelligent solutions.\n" +
        "💡 Passionate about innovation, automation, and smart technologies.\n" +
        "📊 Currently building projects in Computer Vision, and AI-powered systems.",
        "",
        "",
        "📬 Contact Information:\n\n" +
        "📧 Email: purnika568@gmail.com\n" +
        "🔗 LinkedIn: linkedin.com/in/purnika-n-d-j-7957682a6\n" +
        "🐙 GitHub: github.com/Purnika5\n" +
        "📱 Phone: +91-XXXXXXXXXX\n" +
        "📍 Location: India"
    };

    private class Particle {
        double x, y, speed;
        Particle(int width, int height) {
            x = Math.random() * width;
            y = Math.random() * height;
            speed = 0.5 + Math.random() * 1.5;
        }
        void move(int height) {
            y += speed;
            if (y > height) y = 0;
        }
    }
    private Particle[] particles = new Particle[200];

    // Certificates
    private String[][] certificates = {
        {"Generative AI", "Microsoft"},
        {"CSS3", "Infosys"},
        {"Java", "NPTEL"},
        {"C++", "Coursera"},
        {"DSA", "Udemy"},
        {"DBMS", "Coursera"},
        {"C Programming", "Udemy"},
        {"BIG DATA", "IBM"}
    };
    private String[] certificateLinks = {
        "https://drive.google.com/file/d/1I3OT3MpyJwARY0Nhz6hBWUWxrvf0rKyV/view",
        "https://drive.google.com/file/d/1HxU0mZOuymPJE73e_tcDK9A7-xU6WcOL/view",
        "https://drive.google.com/file/d/1HwyMbUcIxa5Uq8f0nJ-0ULmBvT85bD3y/view",
        "https://drive.google.com/file/d/10Ien0suE9Xl7xKtjvUKq_DGsc2cu7zS8/view",
        "https://drive.google.com/file/d/1duiIboBt3q1VYVHZgLL-YmAwJV7X0OkU/view",
        "https://drive.google.com/file/d/1Ipm88Q-GzB4CsoMsyUFh5eBSTJwQ4Zls/view",
        "https://drive.google.com/file/d/1s8P7mPmO_-nbVio1FrhkWivpPN7nrg0A/view",
        "https://drive.google.com/file/d/1HyjFACOGfuCfoWg5_FQEHIZ908b3N-nI/view"
    };

    // Achievements
    private String[][] achievements = {
        {"Certificate of Achievement", "TCS Icon"},
        {"Mechanical Workshop","SRM University"}
    };
    private String[] achievementLinks = {
        "https://drive.google.com/file/d/1HsUXakvxu6pbKU5PfOqUrkrYg3VPmUuO/view",
        "https://drive.google.com/file/d/1sOAjNlODlCmvKGShTofSrihhxOugfDxk/view"
    };

    // Projects
    private String[][] projects = {
        {"Drowsy Driver Detection Smart Specs",
         "Designed smart glasses using sensors and a microcontroller to detect driver drowsiness in real time. The system monitors eye blinks and head tilt, triggering vibration alerts to prevent accidents. This project improves road safety and shows the use of IoT and wearable technology in transportation"},
        {"AI Agent",
          "Created a Python-Selenium based automation agent to perform web tasks such as opening sites, logging in, searching, and extracting data. It reduces manual effort and highlights AI-driven automation for tasks like data collection and testing."},
        {"Notepad",
            "Developed an Advanced Notepad application in Java with enhanced text editing features beyond the standard Notepad. It supports file operations (create, open, save, edit), search and replace functionality, customizable font and theme settings, and user-friendly shortcuts for faster editing. This project showcases Java Swing for GUI design and file handling concepts, providing both simplicity for everyday use and flexibility for advanced users."
        }
    };
    private String[] projectLinks = {
        "https://github.com/Purnika5/Drowsy-Driver-Detection", 
        "https://github.com/Purnika5/Ai-agent",
        "https://github.com/Purnika5/Notepad"
    };

    public PortfolioApp() {
        setBackground(new Color(15, 17, 26));
        for(int i=0;i<particles.length;i++) particles[i] = new Particle(1920, 1080);
        timer = new Timer(16, this);
        timer.start();
        addMouseListener(this);
        addMouseMotionListener(this);

        addMouseWheelListener(e -> {
            if(currentSection == 1 || currentSection == 2) {
                scrollOffset += e.getWheelRotation() * 20;
                scrollOffset = Math.max(0, Math.min(scrollOffset, 1000));
                repaint();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(Particle p : particles) p.move(getHeight());
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int WIDTH = getWidth(), HEIGHT = getHeight();
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        drawBackground(g2d, WIDTH, HEIGHT);
        drawNavigationBar(g2d, WIDTH);

        switch(currentSection) {
            case 0 -> drawCenteredHomeText(g2d, WIDTH, HEIGHT);
            case 1 -> drawSkillsCertificatesAchievements(g2d, WIDTH, HEIGHT);
            case 2 -> drawProjects(g2d, WIDTH, HEIGHT);
            default -> drawFloatingText(g2d, WIDTH, HEIGHT);
        }
    }

    private void drawBackground(Graphics2D g2d, int WIDTH, int HEIGHT) {
        g2d.setColor(new Color(15, 17, 26));
        g2d.fillRect(0, 0, WIDTH, HEIGHT);
        g2d.setColor(new Color(0, 255, 128, 120));
        for(Particle p : particles) g2d.fillRect((int)p.x, (int)p.y, 2, 2);
    }

    private void drawNavigationBar(Graphics2D g2d, int WIDTH) {
        g2d.setColor(new Color(25, 30, 45, 220));
        g2d.fillRoundRect(10, 10, WIDTH-20, 60, 30, 30);

        int buttonWidth = 140, buttonHeight = 40, startX = 30, startY = 15;
        for(int i=0;i<sections.length;i++){
            Color buttonColor = (i == currentSection) ? new Color(0, 191, 255) : new Color(50, 60, 90);
            if(mouseOverButton(i)) buttonColor = buttonColor.brighter();

            g2d.setColor(buttonColor);
            g2d.fillRoundRect(startX + i*(buttonWidth+15), startY, buttonWidth, buttonHeight, 20, 20);

            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("SansSerif", Font.BOLD, 15));
            FontMetrics fm = g2d.getFontMetrics();
            int textX = startX + i*(buttonWidth+15) + (buttonWidth - fm.stringWidth(sections[i]))/2;
            int textY = startY + (buttonHeight + fm.getAscent() - fm.getDescent())/2;
            g2d.drawString(sections[i], textX, textY);
        }
    }

    private boolean mouseOverButton(int index){
        int mx = lastMouseX, my = lastMouseY;
        int buttonWidth = 140, buttonHeight = 40, startX = 30, startY = 15;
        int x = startX + index*(buttonWidth+15), y = startY;
        return mx >= x && mx <= x+buttonWidth && my >= y && my <= y+buttonHeight;
    }

    private void drawCenteredHomeText(Graphics2D g2d, int WIDTH, int HEIGHT){
        String[] lines = portfolioContent[currentSection].split("\n");
        int totalHeight = lines.length * 40, startY = (HEIGHT - totalHeight)/2;
        for(int i=0;i<lines.length;i++){
            String line = lines[i].trim();
            if(i==0){
                g2d.setColor(new Color(0, 255, 255));
                g2d.setFont(new Font("SansSerif", Font.BOLD, 36));
            } else if(line.contains("Building Intelligent Solutions With Code")) {
                g2d.setColor(new Color(0, 191, 255));
                g2d.setFont(new Font("Serif", Font.ITALIC + Font.BOLD, 28));
            } else {
                g2d.setColor(Color.WHITE);
                g2d.setFont(new Font("SansSerif", Font.PLAIN, 20));
            }
            FontMetrics fm = g2d.getFontMetrics();
            int textWidth = fm.stringWidth(line);
            int x = (WIDTH - textWidth)/2;
            int y = startY + i*40;
            g2d.drawString(line, x, y);
        }
    }

    private int drawWrappedText(Graphics2D g2d, String text, int x, int y, int maxWidth, int lineHeight) {
        FontMetrics fm = g2d.getFontMetrics();
        String[] words = text.split(" ");
        StringBuilder line = new StringBuilder();
        int curY = y, lines = 0;
        for (String word : words) {
            String testLine = line + word + " ";
            int textWidth = fm.stringWidth(testLine);
            if (textWidth > maxWidth) {
                g2d.drawString(line.toString(), x, curY);
                line = new StringBuilder(word + " ");
                curY += lineHeight; lines++;
            } else line = new StringBuilder(testLine);
        }
        g2d.drawString(line.toString(), x, curY);
        lines++; return lines;
    }

    private int estimateLineCount(String text, int maxWidth, FontMetrics fm){
        String[] words = text.split(" ");
        int lines=1; StringBuilder line = new StringBuilder();
        for(String word : words){
            String testLine = line + word + " ";
            if(fm.stringWidth(testLine) > maxWidth){
                lines++; line = new StringBuilder(word + " ");
            } else line = new StringBuilder(testLine);
        }
        return lines;
    }

    private void drawSkillsCertificatesAchievements(Graphics2D g2d, int WIDTH, int HEIGHT){
        int startY = 100 - scrollOffset;
        int blockWidth = (WIDTH - 200)/3;
        int padding = 15;
        int lineHeight = 20;

        // Skills
        String[][] skills = {
            {"Languages", "C, C++, Java, Python, JavaScript, HTML, CSS, SQL"},
            {"Frameworks", "Pandas, Spring Boot"},
            {"Tools", "PowerPoint, Canva, Excel, MySQL, Bootstrap"},
            {"Platforms", "PyCharm, VS Code, NetBeans, Jupyter Notebook"}
        };
        int x = 50;
        g2d.setFont(new Font("SansSerif", Font.BOLD, 20));
        g2d.setColor(new Color(0,255,255));
        g2d.drawString("Skills", x, startY);
        int curY = startY + 30;

        g2d.setFont(new Font("SansSerif", Font.PLAIN, 16));
        for(String[] skill : skills){
            int lines = estimateLineCount(skill[1], blockWidth - 2*padding, g2d.getFontMetrics());
            int boxHeight = lines * lineHeight + 40;
            g2d.setColor(new Color(50,70,100,200));
            g2d.fillRoundRect(x, curY, blockWidth, boxHeight, 20, 20);
            g2d.setColor(new Color(0,255,255));
            g2d.drawString(skill[0], x+padding, curY + 25);
            g2d.setColor(Color.WHITE);
            drawWrappedText(g2d, skill[1], x+padding, curY + 45, blockWidth - 2*padding, lineHeight);
            curY += boxHeight + 10;
        }

        // Certificates
        x += blockWidth + 20;
        curY = startY;
        g2d.setFont(new Font("SansSerif", Font.BOLD, 20));
        g2d.setColor(new Color(0,255,255));
        g2d.drawString("Certificates", x, curY);
        curY += 30;
        g2d.setFont(new Font("SansSerif", Font.PLAIN, 16));

        for(int i=0;i<certificates.length;i++){
            int lines = estimateLineCount(certificates[i][1], blockWidth - 2*padding, g2d.getFontMetrics());
            int boxHeight = lines * lineHeight + 40;
            g2d.setColor(new Color(50,70,100,200));
            g2d.fillRoundRect(x, curY, blockWidth, boxHeight, 20, 20);
            g2d.setColor(new Color(0,255,255));
            g2d.drawString(certificates[i][0], x+padding, curY + 25);
            g2d.setColor(Color.WHITE);
            drawWrappedText(g2d, certificates[i][1], x+padding, curY + 45, blockWidth - 2*padding, lineHeight);
            curY += boxHeight + 10;
        }

        // Achievements
        x += blockWidth + 20;
        curY = startY;
        g2d.setFont(new Font("SansSerif", Font.BOLD, 20));
        g2d.setColor(new Color(0,255,255));
        g2d.drawString("Achievements", x, curY);
        curY += 30;
        g2d.setFont(new Font("SansSerif", Font.PLAIN, 16));
        for(int i=0;i<achievements.length;i++){
            int lines = estimateLineCount(achievements[i][1], blockWidth - 2*padding, g2d.getFontMetrics());
            int boxHeight = lines * lineHeight + 40;
            g2d.setColor(new Color(50,70,100,200));
            g2d.fillRoundRect(x, curY, blockWidth, boxHeight, 20, 20);
            g2d.setColor(new Color(0,255,255));
            g2d.drawString(achievements[i][0], x+padding, curY + 25);
            g2d.setColor(Color.WHITE);
            drawWrappedText(g2d, achievements[i][1], x+padding, curY + 45, blockWidth - 2*padding, lineHeight);
            curY += boxHeight + 10;
        }
    }

    private void drawProjects(Graphics2D g2d, int WIDTH, int HEIGHT) {
        int startX = 50, startY = 100 - scrollOffset, blockWidth = WIDTH - 100, lineHeight = 20;
        g2d.setFont(new Font("SansSerif", Font.BOLD, 24));
        g2d.setColor(new Color(0, 255, 255));
        g2d.drawString("Projects", startX, startY);

        g2d.setFont(new Font("SansSerif", Font.PLAIN, 16));
        int curY = startY + 30;

        for(int i=0;i<projects.length;i++){
            int lines = estimateLineCount(projects[i][1], blockWidth - 30, g2d.getFontMetrics());
            int boxHeight = lines * lineHeight + 40;
            g2d.setColor(new Color(60,80,120,200));
            g2d.fillRoundRect(startX, curY, blockWidth, boxHeight, 20,20);
            g2d.setColor(new Color(0,255,255));
            g2d.drawString(projects[i][0], startX+15, curY+25);
            g2d.setColor(Color.WHITE);
            drawWrappedText(g2d, projects[i][1], startX+15, curY+45, blockWidth-30, lineHeight);
            curY += boxHeight + 10;
        }
    }

    private void drawFloatingText(Graphics2D g2d, int WIDTH, int HEIGHT){
        int startX = WIDTH/4, startY = HEIGHT/5;
        String[] lines = portfolioContent[currentSection].split("\n");
        for(int i=0;i<lines.length;i++){
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("SansSerif", Font.PLAIN, 18));
            g2d.drawString(lines[i], startX, startY + i*28);
        }
    }

    @Override public void mousePressed(MouseEvent e){ lastMouseX=e.getX(); lastMouseY=e.getY(); }
    @Override public void mouseReleased(MouseEvent e){} 
    @Override public void mouseDragged(MouseEvent e){ lastMouseX=e.getX(); lastMouseY=e.getY(); }

    @Override public void mouseMoved(MouseEvent e){ lastMouseX=e.getX(); lastMouseY=e.getY(); repaint(); }

    @Override
    public void mouseClicked(MouseEvent e) {
        int mx = e.getX(), my = e.getY();
        int buttonWidth = 140, buttonHeight = 40, startX = 30, startY = 15;
        for(int i=0;i<sections.length;i++){
            int x = startX + i*(buttonWidth+15), y = startY;
            if(mx >= x && mx <= x+buttonWidth && my >= y && my <= y+buttonHeight){
                currentSection = i; scrollOffset=0; repaint();
                return;
            }
        }
    }

    @Override public void mouseEntered(MouseEvent e){}
    @Override public void mouseExited(MouseEvent e){}

    public static void main(String[] args){
        JFrame frame = new JFrame("3D Portfolio");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.add(new PortfolioApp());
        frame.setVisible(true);
    }
}
