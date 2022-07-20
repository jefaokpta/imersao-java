import java.awt.Color
import java.awt.Font
import java.awt.Graphics2D
import java.awt.image.BufferedImage
import java.io.File
import java.io.InputStream
import javax.imageio.ImageIO


/**
 * @author Jefferson Alves Reis (jefaokpta) < jefaokpta@hotmail.com >
 * Date: 19/07/22
 */
class FigureFactory {

    fun create(inputStream: InputStream, outputName: String) {

        // leitura da imagem
        // InputStream inputStream = new URL(url).openStream();
        val imagemOriginal = ImageIO.read(inputStream)

        // cria nova imagem em mémoria com transparência e com tamanho novo
        val largura = imagemOriginal.width
        val altura = imagemOriginal.height
        val novaAltura = altura + 200
        val novImagem = BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT)

        // copia nova imagem original para o nova imagem (em memória)
        val graphics = novImagem.graphics as Graphics2D
        graphics.drawImage(imagemOriginal, 0, 0, null)

        // configurar a fonte
        val fonte = Font(Font.SANS_SERIF, Font.BOLD, 64)
        graphics.color = Color.YELLOW
        graphics.font = fonte

        // escreve uma frase na nova imagem
        graphics.drawString("TOPZERA", 100, novaAltura - 100)

        //escreve a nova imagem em um arquivo
        val outputFolder = File("saida")
        if (!outputFolder.exists()) {
            outputFolder.mkdir()
        }
        ImageIO.write(novImagem, "png", File("saida/$outputName.png"))
    }

}