package com.jianhaoweb.play;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import javax.swing.border.LineBorder;

import static org.mockito.Mockito.*;

class DinoGmTest {

    private DinoGm gameFrame;

    @Test
    @DisplayName("Should set the game frame to non-resizable")
    void mainSetsGameFrameToNonResizable() {
        gameFrame = mock(DinoGm.class);
        DinoGm.main(new String[]{});
        verify(gameFrame).setResizable(false);
    }

    @Test
    @DisplayName("Should set the game frame to visible")
    void mainSetsGameFrameToVisible() {
        gameFrame = mock(DinoGm.class);
        JFrame frame = mock(JFrame.class);

        when(gameFrame.createFrame()).thenReturn(frame);

        DinoGm.main(new String[]{});

        verify(frame, times(1)).setVisible(true);
    }

    @Test
    @DisplayName("Should set the game frame's content pane to a new GamePanel")
    void mainSetsGameFrameContentPaneToNewGamePanel() {
        JFrame mockFrame = mock(JFrame.class);
        when(mockFrame.getContentPane()).thenReturn(mock(Container.class));
        doNothing().when(mockFrame).setContentPane(any(Container.class));

        doReturn(mockFrame).when(spy(DinoGm.class)).createFrame();

        DinoGm.main(new String[]{});

        verify(mockFrame).setContentPane(any(GamePanel.class));
    }

    @Test
    @DisplayName("Should initialize the game frame correctly")
    void mainInitializesGameFrameCorrectly() {
        JFrame mockFrame = mock(JFrame.class);
        when(mockFrame.getContentPane()).thenReturn(mock(Container.class));
        when(mockFrame.getContentPane().add(any(Component.class))).thenReturn(null);
        when(mockFrame.getContentPane().setBackground(any(Color.class))).thenReturn(null);
        when(mockFrame.getContentPane().setPreferredSize(any(Dimension.class))).thenReturn(null);
        when(mockFrame.getContentPane().setBorder(any(Border.class))).thenReturn(null);
        when(mockFrame.pack()).thenReturn(null);
        when(mockFrame.setLocationRelativeTo(null)).thenReturn(null);
        when(mockFrame.setResizable(false)).thenReturn(null);
        when(mockFrame.setVisible(true)).thenReturn(null);

        PowerMockito.mockStatic(SwingUtilities.class);
        PowerMockito.when(SwingUtilities.invokeLater(any(Runnable.class))).thenAnswer(invocation -> {
            Runnable runnable = invocation.getArgument(0);
            runnable.run();
            return null;
        });

        PowerMockito.whenNew(JFrame.class).withArguments(anyString()).thenReturn(mockFrame);

        DinoGm.main(new String[]{});

        verify(mockFrame).setUndecorated(true);
        verify(mockFrame).setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        verify(mockFrame).setContentPane(any(GamePanel.class));
        verify(mockFrame).pack();
        verify(mockFrame).setLocationRelativeTo(null);
        verify(mockFrame).setResizable(false);
        verify(mockFrame).setVisible(true);
    }

    @Test
    @DisplayName("Should set the game frame's default close operation to EXIT_ON_CLOSE")
    void mainSetsGameFrameCloseOperationToExitOnClose() {
        JFrame frame = mock(JFrame.class);
        when(frame.getContentPane()).thenReturn(mock(Container.class));
        when(frame.getContentPane().getPreferredSize()).thenReturn(new Dimension(300, 150));
        when(frame.getContentPane().getBackground()).thenReturn(Color.WHITE);
        when(frame.getContentPane().getBorder()).thenReturn(new LineBorder(Color.WHITE, 2));
        when(frame.getContentPane().getFocusable()).thenReturn(true);
        when(frame.getContentPane().addKeyListener(any(KeyListener.class))).thenReturn(null);
        when(frame.getContentPane().addMouseListener(any(MouseAdapter.class))).thenReturn(null);
        when(frame.getContentPane().addMouseMotionListener(any(MouseAdapter.class))).thenReturn(null);
        when(frame.getContentPane().setPreferredSize(any(Dimension.class))).thenReturn(null);
        when(frame.getContentPane().setBackground(any(Color.class))).thenReturn(null);
        when(frame.getContentPane().setBorder(any(Border.class))).thenReturn(null);
        when(frame.getContentPane().setFocusable(anyBoolean())).thenReturn(null);
        when(frame.getContentPane().addKeyListener(any(KeyListener.class))).thenReturn(null);
        when(frame.getContentPane().addMouseListener(any(MouseAdapter.class))).thenReturn(null);
        when(frame.getContentPane().addMouseMotionListener(any(MouseAdapter.class))).thenReturn(null);
        when(frame.pack()).thenReturn(null);
        when(frame.setLocationRelativeTo(null)).thenReturn(null);
        when(frame.setResizable(false)).thenReturn(null);
        when(frame.setVisible(true)).thenReturn(null);

        SwingUtilities mockSwingUtilities = mock(SwingUtilities.class);
        doAnswer(invocation -> {
            Runnable runnable = invocation.getArgument(0);
            runnable.run();
            return null;
        }).when(mockSwingUtilities).invokeLater(any(Runnable.class));

        PowerMockito.mockStatic(SwingUtilities.class);
        when(SwingUtilities.invokeLater(any(Runnable.class))).thenAnswer(invocation -> {
            Runnable runnable = invocation.getArgument(0);
            runnable.run();
            return null;
        });

        PowerMockito.mockStatic(JFrame.class);
        when(JFrame.class, "new", any(String.class)).thenReturn(frame);

        DinoGm.main(new String[]{});

        verify(frame, times(1)).setUndecorated(true);
        verify(frame, times(1)).setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        verify(frame, times(1)).setContentPane(any(GamePanel.class));
        verify(frame, times(1)).pack();
        verify(frame, times(1)).setLocationRelativeTo(null);
        verify(frame, times(1)).setResizable(false);
        verify(frame, times(1)).setVisible(true);
    }

}