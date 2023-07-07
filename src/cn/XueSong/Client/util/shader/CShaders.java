package cn.XueSong.Client.util.shader;

import cn.XueSong.Client.util.shader.impl.*;

public interface CShaders {
    CQShader CQ_SHADER = new CQShader();
    CGQShader CGQ_SHADER = new CGQShader();
    COQShader COQ_SHADER = new COQShader();
    COGQShader COGQ_SHADER = new COGQShader();
}
