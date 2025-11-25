 import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CodingPortfolio extends JPanel implements ActionListener, MouseListener, MouseMotionListener {
    private Timer timer;
    private int lastMouseX = 0, lastMouseY = 0;
    private int currentSection = 0;
    private float animationAngle = 0;

    private String[] sections = {"HOME", "SKILLS", "ABOUT", "CONTACT"};

    private String[] portfolioContent = {
        // HOME PAGE
        "👋 Hi, I'm Purnika Naga Durga Jyothi\n" +
        "💡 Building Intelligent Solutions With Code\n" +
        "🤖 AI & Coding Enthusiast, turning ideas into intelligent solutions.\n" +
        "💡 Passionate about innovation, automation, and smart technologies.\n" +
        "📊 Currently building projects in Computer Vision, and AI-powered systems.",

        // SKILLS PAGE
        "🛠️ Skills Summary\n" +
        "Languages:\n💻 C, 💻 C++, ☕ Java, 🐍 Python, 🌐 JavaScript, 📄 HTML, 🎨 CSS, 🗄️ SQL\n\n" +
        "Frameworks:\n🐼 Pandas, 🌱 Spring Boot\n\n" +
        "Tools:\n📊 PowerPoint, 🎨 Canva, 📈 Excel, 🗄️ MySQL, 📦 Bootstrap\n\n" +
        "Platforms:\n🐍 PyCharm, 💻 VS Code, ☕ NetBeans",

        // ABOUT PAGE
        "👩‍💻 About Me:\n\n" +
        "I am an aspiring AI and software developer passionate about\n" +
        "creating innovative solutions that merge coding and intelligence.\n\n" +
        "🔹 Specializations:\n" +
        "• 🤖 Artificial Intelligence & Machine Learning\n" +
        "• 💻 Java & Python Programming\n" +
        "• 🎨 3D Graphics & Algorithms\n" +
        "• 🌐 Smart Systems (IoT + AI)",

        // CONTACT PAGE
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

    public CodingPortfolio() {
        setBackground(new Color(15, 17, 26));
        for(int i=0;i<particles.length;i++)
            particles[i] = new Particle(1920, 1080);
        timer = new Timer(16, this);
        timer.start();
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(Particle p : particles) p.move(getHeight());
        animationAngle += 0.02f; // floating animation
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int WIDTH = getWidth();
        int HEIGHT = getHeight();

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        drawBackground(g2d, WIDTH, HEIGHT);
        drawNavigationBar(g2d, WIDTH);

        if(currentSection == 0) {
            drawCenteredHomeText(g2d, WIDTH, HEIGHT);
        } else if(currentSection == 1) {
            drawSkillsPage(g2d, WIDTH, HEIGHT);
        } else {
            drawFloatingText(g2d, WIDTH, HEIGHT);
        }
    }

    private void drawBackground(Graphics2D g2d, int WIDTH, int HEIGHT) {
        g2d.setColor(new Color(15, 17, 26));
        g2d.fillRect(0, 0, WIDTH, HEIGHT);

        g2d.setColor(new Color(0, 255, 128, 120));
        for(Particle p : particles)
            g2d.fillRect((int)p.x, (int)p.y, 2, 2);

        g2d.setColor(new Color(30, 35, 50, 60));
        for(int x=0;x<WIDTH;x+=60) g2d.drawLine(x,0,x,HEIGHT);
        for(int y=0;y<HEIGHT;y+=60) g2d.drawLine(0,y,WIDTH,y);
    }

    private void drawNavigationBar(Graphics2D g2d, int WIDTH) {
        g2d.setColor(new Color(25, 30, 45, 220));
        g2d.fillRoundRect(10, 10, WIDTH-20, 60, 30, 30);

        int buttonWidth = 140, buttonHeight = 40;
        int startX = 30, startY = 15;

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
        int buttonWidth = 140, buttonHeight = 40;
        int startX = 30, startY = 15;
        int x = startX + index*(buttonWidth+15), y = startY;
        return mx >= x && mx <= x+buttonWidth && my >= y && my <= y+buttonHeight;
    }

    private void drawCenteredHomeText(Graphics2D g2d, int WIDTH, int HEIGHT){
        String[] lines = portfolioContent[currentSection].split("\n");
        int totalHeight = lines.length * 40;
        int startY = (HEIGHT - totalHeight)/2;

        for(int i=0;i<lines.length;i++){
            String line = lines[i].trim();
            if(i==0){
                g2d.setColor(new Color(0, 255, 255));
                g2d.setFont(new Font("SansSerif", Font.BOLD, 36));
            }
            else if(line.contains("Building Intelligent Solutions With Code")) {
                g2d.setColor(new Color(0, 191, 255));
                g2d.setFont(new Font("Serif", Font.ITALIC + Font.BOLD, 28));
            }
            else {
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

    // Floating Skills Page
    private void drawSkillsPage(Graphics2D g2d, int WIDTH, int HEIGHT) {
        String[] categories = {
            "Languages:\n💻 C, 💻 C++, ☕ Java, 🐍 Python\n",
            "Languages:\njaScript, 📄 HTML, 🎨 CSS, 🗄️ SQL\n",
            "Frameworks:\n🐼 Pandas, 🌱 Spring Boot",
            "Tools:\n📊 PowerPoint, 🎨 Canva, 📈 Excel, 🗄️ MySQL, 📦 Bootstrap",
            "Platforms:\n🐍 PyCharm, 💻 VS Code, ☕ NetBeans"
        };

        int boxWidth = WIDTH/5;
        int boxHeight = 120;
        int centerX = WIDTH/2;
        int centerY = HEIGHT/2;

        for(int i=0;i<categories.length;i++){
            int row = i/2, col = i%2;
            int x = centerX - boxWidth - 40 + col*(boxWidth+80);
            int y = centerY - boxHeight - 40 + row*(boxHeight+150);

            // floating effect
            int floatOffset = (int)(10*Math.sin(animationAngle + i));

            GradientPaint gradient = new GradientPaint(x, y, new Color(0, 80, 160, 200),
                                                       x+boxWidth, y+boxHeight, new Color(0, 180, 200, 150));
            g2d.setPaint(gradient);
            g2d.fillRoundRect(x, y + floatOffset, boxWidth, boxHeight, 25, 25);

            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("SansSerif", Font.PLAIN, 16));

            String[] lines = categories[i].split("\n");
            int textY = y + 40 + floatOffset;
            for(String line : lines){
                if(line.endsWith(":")){
                    g2d.setColor(new Color(255, 215, 0));
                    g2d.setFont(new Font("SansSerif", Font.BOLD, 20));
                } else {
                    g2d.setColor(Color.WHITE);
                    g2d.setFont(new Font("Monospaced", Font.PLAIN, 16));
                }
                g2d.drawString(line, x+20, textY);
                textY += 25;
            }
        }
    }

    private void drawFloatingText(Graphics2D g2d, int WIDTH, int HEIGHT){
        int startX = WIDTH/8;
        int startY = HEIGHT/6;
        String[] lines = portfolioContent[currentSection].split("\n");

        for(int i=0;i<lines.length;i++){
            String line = lines[i].trim();
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("SansSerif", Font.PLAIN, 18));
            g2d.drawString(line, startX, startY + i*28);
        }
    }

    @Override public void mousePressed(MouseEvent e){ lastMouseX=e.getX(); lastMouseY=e.getY(); }
    @Override public void mouseReleased(MouseEvent e){}
    @Override public void mouseDragged(MouseEvent e){ lastMouseX=e.getX(); lastMouseY=e.getY(); }
    @Override public void mouseClicked(MouseEvent e){
        int clickX = e.getX(), clickY = e.getY();
        if(clickY>=15 && clickY<=55){
            int index = (clickX-30)/155;
            if(index>=0 && index<sections.length) currentSection=index;
        }
    }
    @Override public void mouseEntered(MouseEvent e){}
    @Override public void mouseExited(MouseEvent e){}
    @Override public void mouseMoved(MouseEvent e){ lastMouseX=e.getX(); lastMouseY=e.getY(); }

    public static void main(String[] args){
        JFrame frame = new JFrame("AI & Coding Portfolio");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new CodingPortfolio());
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }
}
